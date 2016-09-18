package com.example.examinationsystem;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class Instruction_page_2 extends Activity {
	Button bt1;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.instruction_page_2);
	       
	    }
public void onClick(View view){
	 bt1= (Button) findViewById(R.id.button10);
     
	Intent i= new Intent(Instruction_page_2.this,Choose_department_page.class);
	startActivity(i);
	

}


}
