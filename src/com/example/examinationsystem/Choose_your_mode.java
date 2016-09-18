package com.example.examinationsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Choose_your_mode extends Activity {
    TextView tv1;
    TextView tv2;
    Intent i,j;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_your_mode);
		
		
	}
    public void offline(View view){
    	
    	i= new Intent(Choose_your_mode.this, Login_page.class);
    	startActivity(i);
    	
    }
    public void online(View view){
    
    	j= new Intent(Choose_your_mode.this, Online_login_page.class);
    	startActivity(j);
    }
}
