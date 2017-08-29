package project.webapp;

import android.content.Context;

public class WebInterface {

	WebCal web=null;
	double a=0.00;
	double b=0.00;
	double s=0.00;
	String op="";
	public WebInterface(Context c){
		web=(WebCal)c;
	}
	
	public void addNum(double num){
		
			b=num;
		
	}
	
	public void addOperator(String opr){
		op=opr;
		a=b;
		b=0.00;
	
	}
	
	public double getResult(){
		if(op.equals("+")){
			return a+b;
		}
		else if(op.equals("-")){
			return a-b;
		}
		else if(op.equals("*")){
			return a*b;
		}
		else if(op.equals("/")){
			return a/b;
		}
		else{
			return 0.00;
		}
				
	}

	
	
}
