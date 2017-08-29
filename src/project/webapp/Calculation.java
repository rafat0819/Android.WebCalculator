package project.webapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Calculation extends Activity{
	
	FileOutputStream fos = null;
	String FILENAME = "log.txt";
	
	public void Log(String value1, String value2, double result, String sign){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String CurrentDateTime = dateFormat.format(cal.getTime());
		
		String data = "" + CurrentDateTime + " : " + value1 + " " + sign + " " + value2 + " = " + result + "\n";
		
		try {
			
			fos = openFileOutput(FILENAME, Context.MODE_APPEND);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fos.write(data.getBytes());
			fos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public void LogDB(String value1, String value2, double result, String sign){
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String CurrentDateTime = dateFormat.format(cal.getTime());
	
    	
    	DB_Log log=new DB_Log(CurrentDateTime,value1, value2, sign, result);
    	DB_Handler db=new DB_Handler(this);
    	
    	
    	
    	db.addLog(log);
    	Toast.makeText(getApplicationContext(),"Added to db",Toast.LENGTH_SHORT).show();
}
	
		
	
	
	public void checkSignAndProcess(Intent resultI, String sign, String value1, String value2){
		if(sign.equals("/")){
	    	double varResult = ((Double.parseDouble(value1)) / (Double.parseDouble(value2)));
			
	    	resultI.putExtra("varResult", String.valueOf(varResult));
			setResult(Activity.RESULT_OK, resultI);
			
			Log(value1, value2, varResult, sign);
			LogDB(value1, value2, varResult, sign);
			
			finish();
		}else if(sign.equals("*")){
			double varResult = ((Double.parseDouble(value1)) * (Double.parseDouble(value2)));
				
	    	resultI.putExtra("varResult", String.valueOf(varResult));
			setResult(Activity.RESULT_OK, resultI);
			
			Log(value1, value2, varResult, sign);
			LogDB(value1, value2, varResult, sign);
			
			finish();
		}else if(sign.equals("-")){
			double varResult = ((Double.parseDouble(value1)) - (Double.parseDouble(value2)));
					
	    	resultI.putExtra("varResult", String.valueOf(varResult));
			setResult(Activity.RESULT_OK, resultI);

			Log(value1, value2, varResult, sign);
			LogDB(value1, value2, varResult, sign);
			
			finish();
		}else if(sign.equals("+")){
			System.out.println(value1 + "," + value2);
			double varResult = ((Double.parseDouble(value1)) + (Double.parseDouble(value2)));
			
	    	resultI.putExtra("varResult", String.valueOf(varResult));
			setResult(Activity.RESULT_OK, resultI);
			
			Log(value1, value2, varResult, sign);
			LogDB(value1, value2, varResult, sign);
			
			finish();
		}
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent ResultIntent = new Intent();
        ResultIntent = getIntent();
        
        System.out.println(ResultIntent);
        
        if(ResultIntent != null){
            String varFirst = ResultIntent.getStringExtra("First");
            String varSecond = ResultIntent.getStringExtra("Second");
            String varSign = ResultIntent.getStringExtra("OP");
            
            checkSignAndProcess(ResultIntent, varSign, varFirst, varSecond);
        }else{
        	setResult(Activity.RESULT_CANCELED, ResultIntent);
			finish();
        }
     
    }
}
