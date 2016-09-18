package com.example.examinationsystem;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start_page extends Activity {
	Button bt1;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_page);
		
		
	}
	public void onClick(View view){
		bt1= (Button) findViewById(R.id.button2);
		i= new Intent(Start_page.this,Choose_your_mode.class);
		
		startActivity(i);
		
		
		
	}
}
