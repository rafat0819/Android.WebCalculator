package project.webapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class WebCalculatorActivity extends Activity {
	
	Button btnChkInt;
	String answer;
	boolean flag=true;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnChkInt=(Button)findViewById(R.id.button1);
        
        btnChkInt.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        		if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {		
        		Toast.makeText(getApplicationContext(), "Internet Connection Available" , Toast.LENGTH_LONG).show();
        		Intent i=new Intent(WebCalculatorActivity.this, WebCal.class );
                startActivity(i);
        		}
        		else{
        		Toast.makeText(getApplicationContext(), "no internet", Toast.LENGTH_LONG).show();
        		Intent u=new Intent(WebCalculatorActivity.this, CalculatorFinalActivity.class );
        		startActivity(u);
        		}
        	}        	
        });
        
        
        }
        	
        
        

    
    
}