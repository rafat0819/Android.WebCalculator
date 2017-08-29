package project.webapp;
import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DB_Handler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "logManager";
 
    // Contacts table name
    private static final String TABLE_Log = "log";
 
    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_DATETIME = "datetime";
    public static final String KEY_FIRST = "first";
    public static final String KEY_SECOND = "second";
    public static final String KEY_SIGN = "sign";
    public static final String KEY_RESULT = "result";
 
    public DB_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Log + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_DATETIME + " TEXT," + KEY_FIRST + " TEXT," + KEY_SECOND + " TEXT," + KEY_SIGN + " TEXT," 
				+ KEY_RESULT + " DOUBLE" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Log);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addLog(DB_Log Log) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DATETIME, Log.getDATETIME());
        values.put(KEY_FIRST, Log.getFirst());
        values.put(KEY_SECOND, Log.getSecond());
        values.put(KEY_SIGN, Log.getSign());
        values.put(KEY_RESULT, Log.getResult());
 
        // Inserting Row
        db.insert(TABLE_Log, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
 
 
    // Getting All Contacts
    public List<DB_Log> getAllLog() {
        List<DB_Log> LogList = new ArrayList<DB_Log>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Log;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
        	do {
                DB_Log log= new DB_Log();
                log.setID(Integer.parseInt(cursor.getString(0)));
                log.setDATETIME(cursor.getString(1));
                log.setFirst(cursor.getString(2));
                log.setSecond(cursor.getString(3));
                log.setSign(cursor.getString(4));
                log.setResult(Double.parseDouble(cursor.getString(5)));
                
                // Adding contact to list
                LogList.add(log);
            } while (cursor.moveToNext());
        
        }
        // return contact list
        return LogList;    
    }
}
    
    
 
    
 
