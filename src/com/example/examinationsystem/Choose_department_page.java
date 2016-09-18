package com.example.examinationsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.RadioButton;

	public class Choose_department_page extends Activity {
		
		 protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.choose_department);
		        RadioButton dept1= (RadioButton) findViewById(R.id.radio0);
		        RadioButton dept2= (RadioButton) findViewById(R.id.radio1);
		        if(dept1.isChecked()){
		        	dept1.setChecked(false);
		        	dept2.setChecked(true);
		        }
		        else{
		        	dept1.setChecked(true);
		        	dept2.setChecked(false);
		        }
		       
		        
		        
		    }
		 public void onClick(View view){
			
			 Intent i= new Intent(Choose_department_page.this,Main_exam.class);
			 startActivity(i);
			 
			 
		 }
		
}
