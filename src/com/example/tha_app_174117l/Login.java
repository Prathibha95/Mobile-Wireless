package com.example.tha_app_174117l;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText indexno;
	EditText password;
    Button login;
    ProgressDialog progressDialog;
    static String user_index;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.interface2);
	indexno = (EditText)findViewById(R.id.indexno);
    password = (EditText)findViewById(R.id.password);
    login = (Button)findViewById(R.id.login);
    progressDialog = new ProgressDialog(Login.this);
	
    login.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (TextUtils.isEmpty(indexno.getText().toString())) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				Toast.makeText(getApplicationContext(), "Please enter your index no!", Toast.LENGTH_SHORT).show();
				return;
			}
			
			if (TextUtils.isEmpty(password.getText().toString())) {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				Toast.makeText(getApplicationContext(), "Please enter your password!", Toast.LENGTH_SHORT).show();
				return;
			}
			loginUser();
			
		}		
	});
	
}
	private void loginUser() {
		// TODO Auto-generated method stub
		progressDialog.setMessage("Please wait!!!");
		progressDialog.show();
		
		try {
			DbHelper dbh = new DbHelper(Login.this);
			boolean resL = dbh.loginUser(indexno.getText().toString(), password.getText().toString());
	        dbh.close();
	        
	        if (resL) {
	            Log.d("sql", String.valueOf(resL));
	            user_index = indexno.getText().toString();
	            if (progressDialog.isShowing()) {
	                progressDialog.dismiss();
	            }

	            Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
	            Intent startActivity2 = new Intent(Login.this, Profile.class);
	            startActivity(startActivity2);
	            
	        } else {
	            if (progressDialog.isShowing()) {
	                progressDialog.dismiss();
	            }
	            Toast.makeText(getApplicationContext(), "Incorrect username or Password", Toast.LENGTH_SHORT).show();
	        }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
