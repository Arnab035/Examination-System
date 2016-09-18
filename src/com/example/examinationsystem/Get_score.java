package com.example.examinationsystem;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Get_score extends Activity {
	ArrayList<Integer> answer= new ArrayList<Integer>();
	ArrayList<String> myopt= new ArrayList<String>();
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.get_score);
	        Intent in=getIntent();
	        int ac= in.getIntExtra("CORRECT",0);
	        TextView tv=(TextView) findViewById(R.id.textView2);
	        tv.setText("You have "+ ac +" correct answers");
	        int wrong= in.getIntExtra("WRONG",0);
	        TextView tv1= (TextView) findViewById(R.id.textView3);
	        tv1.setText("You have "+wrong+ " wrong answers");
	        int score= 3*ac+(-1)*wrong;
	        TextView tv3= (TextView) findViewById(R.id.textView5);
	        tv3.setText("YOUR SCORE IS "+ score);
	        answer= in.getIntegerArrayListExtra("ANS");
	         myopt= in.getStringArrayListExtra("MY");
	    }
	
		
		
	
	public void analysis(View view){
		Intent i= new Intent(Get_score.this, Performance_analysis.class);
		ArrayList<Integer> answer1=new ArrayList<Integer>();
		ArrayList<String> myopt1= new ArrayList<String>();
		for(int k=0;k<answer.size();k++)
			answer1.add(answer.get(k));
		for(int l=0;l<myopt.size();l++)
			myopt1.add(myopt.get(l));
		i.putIntegerArrayListExtra("ANS!", answer1);
		i.putStringArrayListExtra("MY!", myopt1);
		startActivity(i);
	}
		
}


