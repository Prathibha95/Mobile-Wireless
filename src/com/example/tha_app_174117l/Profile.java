package com.example.tha_app_174117l;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends MainActivity {
	TextView name;
	TextView indexno;
	TextView email;
	TextView mobile;
	TextView gpa;
	TextView password;
    String name1,index1,email1,mobile1,gpa1,password1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface3);
        name= (TextView)findViewById(R.id.name);
        indexno=(TextView)findViewById(R.id.indexno);
        email=(TextView)findViewById(R.id.email);
        mobile=(TextView)findViewById(R.id.mobile);
        gpa=(TextView)findViewById(R.id.gpa);
        password=(TextView)findViewById(R.id.password);
        
        DbHelper db=new DbHelper(Profile.this);
        Cursor result=db.getUserDetails();

        if (result.moveToFirst()) {
            do {
                 name1 =result.getString(0);
                 index1 =result.getString(1);
                 email1 =result.getString(2);
                 mobile1 =result.getString(3);
                 gpa1 =result.getString(4);
                 password1 =result.getString(5);

            } while (result.moveToNext());
        }

        name.setText(name1);
        indexno.setText(index1);
        email.setText(email1);
        mobile.setText(mobile1);
        gpa.setText(gpa1);
        password.setText(password1);
    }
    
    
    
}
