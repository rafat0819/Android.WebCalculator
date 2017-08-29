package project.webapp;

public class DB_Log {

	private int id;
    private String	Date;
    private String First;
    private String Second;
    private String OP;
    private double Result;
    
    public DB_Log(){
    	this.id=-1;
    	this.Date="";
    	this.First="";
    	this.Second="";
    	this.OP="";
    	this.Result=0.0;
    }
    
    public DB_Log(String _date, String _first, String _second, String _op, double _result){
    	this.Date=_date;
    	this.First=_first;
    	this.Second=_second;
    	this.OP=_op;
    	this.Result=_result;
    }
    
    public void setID(int _id){
    	this.id = _id;
    }
    public int getID(){
    	return this.id;
    }
    
    public void setDATETIME(String _date){
    	this.Date = _date;
    }
    public String getDATETIME(){
    	return this.Date;
    }
    
    public void setFirst(String _first){
    	this.First = _first;
    }
    public String getFirst(){
    	return this.First;
    }
    
    public void setSecond(String _second){
    	this.Second = _second;
    }
    public String getSecond(){
    	return this.Second;
    }
    
    public void setSign(String _op){
    	this.OP = _op;
    }
    public String getSign(){
    	return this.OP;
    }
    
    public void setResult(double _result){
    	this.Result = _result;
    }
    public double getResult(){
    	return this.Result;
    }
    
    
    
}
