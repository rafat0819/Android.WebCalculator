package project.webapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculatorFinalActivity extends Activity {
	
	DB_Handler db=new DB_Handler(this);
	private EditText text_Result,text_Log;
	
	
	private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	private Button btn_Dot, btn_Plus, btn_Minus, btn_Multi, btn_Div, btn_Equals;
	private Button btn_Clear, btn_CE;
	private Button btn_MP, btn_MM, btn_M, btn_MS;
	private Button btn_Show, btn_LClr, btn_Del,btn_Db;
	
	private String First, Second, OP;
	
	String FILENAME = "log.txt";
	
	FileInputStream fis=null;
	FileOutputStream fos=null;
	 
	
	public static final String PREFERENCES_NAME = "Calculator";

	
	String Display="";
	
	public void btnNUM(Button btnV){
		if(this.OP == ""){
			if(this.text_Result.length() < 7){
				Display += btnV.getText().toString();
				this.text_Result.setText(Display);
				
				this.OP = "";
				
				this.First = Display.toString();
			}else{
				Toast.makeText(this, "Cannot exceed 7 characters", Toast.LENGTH_LONG).show();
			}	
		}else{
    		Display += btnV.getText().toString();
			this.text_Result.setText(Display);
		}
    }
	
	public void MemoryOp(Button btnLogic){
    	if(btnLogic.getText().equals("M+")){
    		add(Display);
    		Display = "";
    		this.text_Result.setText(Display);
    	}
		if(btnLogic.getText().equals("M-")){
			sub(Display);
    		Display = "";
    		this.text_Result.setText(Display);
		}
		if(btnLogic.getText().equals("M")){
			show();
		}
		if(btnLogic.getText().equals("MS")){
			store();
		    Display = "";
    		this.text_Result.setText(Display);
		}	
    }
	
	
	
			
	
	
	
	
	
	public void add(String newValue){
    	SharedPreferences sharedPref = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	
		double oldValue = Double.parseDouble(sharedPref.getString("result", "0"));
		editor.putString("result", String.valueOf(Double.parseDouble(newValue) + oldValue));
		editor.commit();
		
		Toast.makeText(this, "Added to the Memory !", Toast.LENGTH_LONG).show();
    }
	
	public void sub(String newValue){
    	SharedPreferences sharedPref = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	
		double oldValue = Double.parseDouble(sharedPref.getString("result", "0"));
		editor.putString("result", String.valueOf(Double.parseDouble(newValue) - oldValue));
		editor.commit();
		
		Toast.makeText(this, "Subtracted and Saved in the Memory !", Toast.LENGTH_LONG).show();
    }
	
	public void show(){
    	SharedPreferences sharedPref = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    	
    	Display = sharedPref.getString("result", "");
    	
    	this.text_Result.setText("" + Display);
    }
	
	public void store(){
    	SharedPreferences sharedPref = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	
    	Display = "" + text_Result.getText();
    	
    	editor.putString("result", Display);
    	editor.commit();
    	
    	Toast.makeText(this, "Saved in the memory !", Toast.LENGTH_LONG).show();
    }
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal);
        
        
        this.OP = "";
        this.First = "";
        
        
        this.text_Result= (EditText)findViewById(R.id.txtResult);
        this.text_Result.setText("0");
        
        this.text_Log= (EditText)findViewById(R.id.txtLog);
        this.text_Log.setText("");
        
        
        this.btn0= (Button)findViewById(R.id.btnZero);
        this.btn1= (Button)findViewById(R.id.btnOne);
        this.btn2= (Button)findViewById(R.id.btnTwo);
        this.btn3= (Button)findViewById(R.id.btnThree);
        this.btn4= (Button)findViewById(R.id.btnFour);
        this.btn5= (Button)findViewById(R.id.btnFive);
        this.btn6= (Button)findViewById(R.id.btnSix);
        this.btn7= (Button)findViewById(R.id.btnSeven);
        this.btn8= (Button)findViewById(R.id.btnEight);
        this.btn9= (Button)findViewById(R.id.btnNine);
        
        this.btn_Dot= (Button)findViewById(R.id.btnDot);
        this.btn_Plus= (Button)findViewById(R.id.btnPlus);
        this.btn_Minus= (Button)findViewById(R.id.btnMinus);
        this.btn_Multi= (Button)findViewById(R.id.btnMulti);
        this.btn_Div= (Button)findViewById(R.id.btnDiv);
        
        this.btn_Clear= (Button)findViewById(R.id.btnClear);
        this.btn_CE= (Button)findViewById(R.id.btnCE);
        
        this.btn_MP= (Button)findViewById(R.id.btnMP);
        this.btn_MM= (Button)findViewById(R.id.btnMM);
        this.btn_MS= (Button)findViewById(R.id.btnMS);
        this.btn_M= (Button)findViewById(R.id.btnM);
           
        this.btn_Equals = (Button)findViewById(R.id.BtnEquals);
        
        this.btn_Show= (Button)findViewById(R.id.btnShow);
        this.btn_LClr= (Button)findViewById(R.id.btnLClr);
        this.btn_Del= (Button)findViewById(R.id.btnDelete);
        this.btn_Db= (Button)findViewById(R.id.btnDBShow);
        this.First="";
        this.Second="";
        
        
        btn0.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn0);
        	}
        });
        
        btn1.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn1);
        	}
        });
        
        btn2.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn2);
        	}
        });
        
        btn3.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn3);
        	}
        });
        
        btn4.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn4);
        	}
        });
        
        btn5.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn5);
        	}
        });
        
        btn6.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn6);
        	}
        });
        
        btn7.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn7);
        	}
        });
        
        btn8.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn8);
        	}
        });
        
        btn9.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn9);
        	}
        });
        
        btn_Dot.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		btnNUM(btn_Dot);
        	}
        });
        
        
        
        btn_Clear.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		Display = "";
        		text_Result.setText("");
        	}
        });
        
        btn_CE.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		if(Display != null || Display != ""){
        			Display = new StringBuilder(text_Result.getText()).deleteCharAt(Display.length() - 1).toString();
        			text_Result.setText(Display);
        		}else{
        			text_Result.setText("");
        		}
        	}
        });
        
        btn_Equals.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){	
    			Second = Display.toString();
    			
    			Intent i = new Intent(getApplicationContext(), Calculation.class);
    			i.putExtra("First", First);
    			i.putExtra("Second", Second);
    			i.putExtra("OP", OP);
    			System.out.println("ashse");
    			startActivityForResult(i, 1);
        	}
        });
        
        btn_Div.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		OP = "/";
        		Display="";
        		text_Result.setText("");
        	}
        });
        
        btn_Multi.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		OP = "*";
        		Display="";
        		text_Result.setText("");
        	}
        });
        
        btn_Minus.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		OP = "-";
        		Display="";
        		text_Result.setText("");
        	}
        });
        	
        
        btn_Plus.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		OP = "+";
        		Display="";
        		text_Result.setText("");
        	}
        });
        
        
        btn_MP.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		MemoryOp(btn_MP);
        	}
        });
        
        btn_MM.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		MemoryOp(btn_MM);
        	}
        });
        
        
        btn_M.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		MemoryOp(btn_M);
        	}
        });
        
        btn_MS.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		MemoryOp(btn_MS);
        	}
        });
        
        btn_Show.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		
				

				try {
					fis = openFileInput(FILENAME);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				InputStreamReader inputStreamReader = new InputStreamReader(fis);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
				StringBuilder sb = new StringBuilder();
				String line;
			
				try {
					while ((line = bufferedReader.readLine()) != null) {
						sb.append(line);
						sb.append("\n");
					}
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			
				try {
					bufferedReader.close();
					inputStreamReader.close();
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				text_Log.setText(sb);
			}
		});
    
        
        btn_Del.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		File dir = getFilesDir();
    			File file = new File(dir, FILENAME);
    			boolean deleted = file.delete();
    			if(deleted){
    	    		Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
    	    		text_Log.setText("");
    			}else{
    	    		Toast.makeText(getApplicationContext(),"Cannot be deleted",Toast.LENGTH_SHORT).show();
    	    	}
    		}
    		
    		
        	
    });
        
        btn_LClr.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		try {
    				fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
    				fos.write(("").getBytes());
    				text_Log.setText("");
    				fos.close();
    				
    				Toast.makeText(getApplicationContext(),"Log Cleared",Toast.LENGTH_SHORT).show();
    			} catch (FileNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		
    		}
    		
    		
        	
        });
        
        btn_Db.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		try{
    				ArrayList<DB_Log> list = (ArrayList<DB_Log>) db.getAllLog();
    				
    				StringBuilder str = new StringBuilder("");
    				
    				for(DB_Log log : list){
    					str.append(log.getDATETIME() + "  :  " + log.getFirst() + " " + log.getSign() + " " + log.getSecond() +
    							" = " + log.getResult() + "\n");
    				}    			
    				text_Log.setText(str.toString());
    			}catch (Exception e){
    				e.printStackTrace();   		
    			}
    		}
    		
    		});
        	
        
    
    
    
    
    
    
    
    }
    
    

        
    
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if(requestCode == 1 && resultCode == RESULT_OK){   		
    		Display = data.getStringExtra("varResult".toString());
    		First = data.getStringExtra("varResult");
    		
			this.text_Result.setText("" + Display);
			
			OP = "";
			Display = "";
    	}
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}