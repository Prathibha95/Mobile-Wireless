package com.example.tha_app_174117l;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import java.util.regex.Pattern;

public class MainActivity extends Activity {
	EditText name;
	EditText indexno; 
	EditText email;
	EditText mobile;
	EditText gpa;
	EditText password;
	EditText repassword;
	Button submit;
	ProgressDialog progressDialog;
	Pattern IndexNoPattern = Pattern.compile("17+[0-9]{4}+[a-zA-Z]{1}");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name=(EditText)findViewById(R.id.name);
		indexno=(EditText)findViewById(R.id.indexno);
		email=(EditText)findViewById(R.id.email);
		mobile=(EditText)findViewById(R.id.mobile);
		gpa=(EditText)findViewById(R.id.gpa);
		password=(EditText)findViewById(R.id.password);
		repassword=(EditText)findViewById(R.id.repassword);
		submit = (Button) findViewById(R.id.submit);
		progressDialog = new ProgressDialog(MainActivity.this);
		
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(name.getText().toString())) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();	
					}
					Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(indexno.getText().toString())) {
					if (progressDialog.isShowing()){
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please enter your index no", Toast.LENGTH_SHORT).show();
					return;
				}
				if (!IndexNoPattern.matcher(indexno.getText().toString()).matches()){
					if (progressDialog.isShowing()){
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please enter valid index no", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(email.getText().toString())) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please enter your email address", Toast.LENGTH_SHORT).show();
					return;
				}
				if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please enter valid email address", Toast.LENGTH_SHORT).show();
					return;
				}
				if (mobile.getText().toString().length() != 10) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please enter your mobile number", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(gpa.getText().toString())) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please enter your current GPA", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(password.getText().toString())) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
					return;
				}
				if (password.getText().toString().length() <6) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please enter a valid password with 6 characters", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(repassword.getText().toString())) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Please confirm your password", Toast.LENGTH_SHORT).show();
					return;
				}
				if (!repassword.getText().toString().equals(password.getText().toString())) {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					Toast.makeText(getApplicationContext(), "Your password is not mayching", Toast.LENGTH_SHORT).show();
					return;
				}
				register();
			}
		});
	}
			private void register() {
				// TODO Auto-generated method stub
				progressDialog.setMessage("Please wait for your registration !");
				progressDialog.show();	
				
//				 Intent startActivity1 = new Intent(MainActivity.this, Login.class);
//	             startActivity(startActivity1);
				
			try {
				DbHelper db = new DbHelper(MainActivity.this);
				Boolean result= db.insertUserData(name.getText().toString(), indexno.getText().toString(), email.getText().toString(), mobile.getText().toString(), gpa.getText().toString(), password.getText().toString());
				Log.d("RESULTTTTTTTTTTTTTTTT",result.toString());
	            if(result){
	                Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
	                if (progressDialog.isShowing()) {
	                    progressDialog.dismiss();
	                    
	                }
	                //open login activity
	                Intent startActivity1 = new Intent(MainActivity.this, Login.class);
	                startActivity(startActivity1);
	            }
	            else {
	                Toast.makeText(getApplicationContext(), "something wrong while inserting the data!", Toast.LENGTH_SHORT).show();
	               
	            }

			}catch (Exception e) {
				 e.printStackTrace();
			}
				
			}


}
