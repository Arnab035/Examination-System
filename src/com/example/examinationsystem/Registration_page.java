package com.example.examinationsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration_page extends Activity {
	
	EditText e1,e2,e3,e4;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_page);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
	}
	public void adddatabase(View view){
		String s1=e1.getText().toString();
		String s2=e2.getText().toString();
		String s3=e3.getText().toString();
		String s4=e4.getText().toString();
		if(!s1.equals("")&&!s2.equals("")&&!s3.equals("")&&!s4.equals("")){
		
		Database entry = new Database(Registration_page.this);
		entry.open();
		entry.createEntry(s1,s2,s3,s4);
		
		Toast.makeText(Registration_page.this,"You have registered successfully",Toast.LENGTH_LONG).show();
		
		entry.close();
		}
		else{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Registration_page.this);
			// set title
						alertDialogBuilder.setTitle("Empty fields????");
						alertDialogBuilder
						.setMessage("Some fields are left blank...please fill to proceed")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.dismiss();
							}
						});
						
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();
						}
	}

	public void showdata(View view){
		Database show = new Database(Registration_page.this);
		show.open();
		String output = show.getData();
		Toast.makeText(Registration_page.this, output, Toast.LENGTH_LONG).show();
		show.close();
	}
}





									
									
									
									
									
									
									
									
									
									
									
									
									
							
		 





