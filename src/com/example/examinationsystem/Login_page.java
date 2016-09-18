package com.example.examinationsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_page extends Activity {
	Button b1,b2;
    EditText e1,e2;
    String s1,s2;
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		
		
		e1=(EditText)findViewById(R.id.editText2);
		e2=(EditText)findViewById(R.id.editText1);
		
		 
		   
		  
		
	}
	public void Login(View view){
		
		s1=e1.getText().toString();
		s2=e2.getText().toString();
		Database entry = new Database(Login_page.this);
		entry.open();
		boolean ans=entry.checkData(s1,s2);
		if(ans){
			Intent intent=new Intent(this,Instruction_page.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(Login_page.this, "Invalid Username or Password",Toast.LENGTH_LONG).show();
			
		}
		
		entry.close();
		
	}
	public void Register(View view){
		Intent intent=new Intent(this,Registration_page.class);
		startActivity(intent);
	}
	

	
}
