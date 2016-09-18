package com.example.examinationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database{
	private static final String DATABASE_NAME="Registrations_exam_1"; //database name
	private static final String TABLE_NAME="Registrations_exam_1_Table"; //table name
	private static final int DATABASE_VERSION=1; //database version

	public static final String KEY_REGNUM="RegistrationNumber";
	public static final String KEY_PASSWORD="Password";
	public static final String KEY_SEMESTER="Semester";
	public static final String KEY_YEAR="year";
	
	
	private DatabaseHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	public class DatabaseHelper extends SQLiteOpenHelper {

		
		
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			 
			 db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" + KEY_REGNUM + " VARCHAR PRIMARY KEY," +
					 KEY_PASSWORD + " VARCHAR ," + KEY_SEMESTER + " VARCHAR ," + KEY_YEAR + " INTEGER  );" );
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int OldVersion, int CurrentVersion) {
			// TODO Auto-generated method stub
			
			db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME ); //drop table if existed
			onCreate(db); //creates table again
		}

	  }
	   
	   public Database(Context c){
		   ourContext = c;
	   }
	   
	   public Database open(){
		   ourHelper = new DatabaseHelper(ourContext);
		   ourDatabase = ourHelper.getWritableDatabase();
		   return this;
		   
	   }

	   public void close(){
		   ourHelper.close();
	   }

	public long createEntry(String RN,String PW, String SEM, String YR) {
		// TODO Auto-generated method stub
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_REGNUM, RN);
		cv.put(KEY_PASSWORD,PW);
		cv.put(KEY_SEMESTER,SEM);
		cv.put(KEY_YEAR,YR);
		return ourDatabase.insert(TABLE_NAME, null, cv);
		
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_REGNUM, KEY_PASSWORD, KEY_SEMESTER, KEY_YEAR};
		Cursor c = ourDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
		String result =" " ;
		int un = c.getColumnIndex(KEY_REGNUM);
		int pw = c.getColumnIndex(KEY_PASSWORD);
		int sem = c.getColumnIndex(KEY_SEMESTER);
		int yr = c.getColumnIndex(KEY_YEAR);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			
			result = result + c.getString(un) + " " + c.getString(pw) + " " +  c.getString(sem)
					 + " " + c.getString(yr) +  "\n" ;
		}
		
		return result;
	}
	public boolean checkData(String Un, String Pw){
		boolean ans=false;
		String[] columns = new String[]{ KEY_REGNUM, KEY_PASSWORD, KEY_SEMESTER, KEY_YEAR};
		Cursor c = ourDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
		int un = c.getColumnIndex(KEY_REGNUM);
		int pw = c.getColumnIndex(KEY_PASSWORD);
		
       for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			
			if(Un.equals(c.getString(un)) && Pw.equals(c.getString(pw))){
			 ans=true;
			break;
			}
			else{
			 ans=false;
			}
			
			
		}
		return ans;
	}

}

