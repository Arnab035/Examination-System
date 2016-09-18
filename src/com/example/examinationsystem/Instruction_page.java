package com.example.examinationsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class Instruction_page extends Activity {
	Button bt1;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.instruction_page);
	        
	    }
   public void onClick(View view){
	   bt1= (Button) findViewById(R.id.button5);
	   Intent i= new Intent(Instruction_page.this,Instruction_page_2.class);
	   startActivity(i);
	   
   }
  

}
