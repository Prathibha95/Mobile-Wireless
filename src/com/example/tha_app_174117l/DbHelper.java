package com.example.tha_app_174117l;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
 
	static int Db_version = 1;
	static String Db_name = "THA_DB.db"; 
    String User_infor = "User_Infor";
	
	public DbHelper(Context context) {
		super(context, Db_name, null, Db_version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
			String user_info_Table = "CREATE TABLE " + User_infor + "("
                + "name" + " TEXT, "
                + "indexno" + " TEXT, "
                + "email" + " TEXT, "
                + "mobile" + " TEXT, "
                + "gpa" + " TEXT, "
                + "password" + " TEXT " + ")";
				db.execSQL(user_info_Table);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + User_infor);
        onCreate(db);
	}

    public boolean insertUserData(String name, String indexno, String email, String mobile, String gpa, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues cValues = new ContentValues();
        
        cValues.put("name", name);
        cValues.put("indexno", indexno);
        cValues.put("email", email);
        cValues.put("mobile", mobile);
        cValues.put("gpa", gpa);
        cValues.put("password", password);

        long result = db.insert(User_infor,null,cValues);
        db.close();

        if(result == -1) {
            Log.d("error message", "error in user Data insert");
            return false;
        }
        else{
            return true;
        }

    }


    //login
    public boolean loginUser(String indexno, String password){
        String[] columns = {"indexno"};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "indexno = ? and password = ?";
        String[] selectionArgs = {indexno, password};

        Cursor cursor = db.query(User_infor, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();

        if(count > 0){
            return true;
        } else {
            return false;      
            } }
    //get user information
    public Cursor getUserDetails(){
        String selectQuery = "SELECT name, indexno, email, mobile, gpa, password FROM User_Infor WHERE indexno = '"+ Login.user_index + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result_Detail = db.rawQuery(selectQuery, null);
        return result_Detail;
    }
}
