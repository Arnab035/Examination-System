package com.example.examinationsystem;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OnlineRegister extends Activity {
	 private ProgressDialog pDialog;
	 
	    JSONParser jsonParser = new JSONParser();
	    EditText inputRollno;
	    EditText inputSemester;
	    EditText inputUserid;
	    EditText inputPassword;
	    EditText branch;
	    private static String url_create_product = "http://10.0.2.2/ecs_arnab/create_user.php";
	    
	    // JSON Node names
	    private static final String TAG_SUCCESS = "success";
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.newregister);
	 
	        // Edit Text
	        inputRollno = (EditText) findViewById(R.id.editText1);
	        inputSemester = (EditText) findViewById(R.id.editText2);
	        inputUserid = (EditText) findViewById(R.id.editText3);
	        inputPassword = (EditText) findViewById(R.id.editText4);
	        branch = (EditText) findViewById(R.id.editText5);
	 
	        // Create button
	        Button btnCreateStudent = (Button) findViewById(R.id.button1);
	 
	        // button click event
	        btnCreateStudent.setOnClickListener(new View.OnClickListener() {
	 
	            @Override
	            public void onClick(View view) {
	                // creating new product in background thread
	                new CreateNewStudent().execute();
	            }
	        });
	        Button btn1 = (Button) findViewById(R.id.button2);
	        
	        btn1.setOnClickListener(new View.OnClickListener() {
	        	
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i= new Intent(OnlineRegister.this, GetAllStudents.class);
					startActivity(i);
					
				}
			});
	    }
	    
	    class CreateNewStudent extends AsyncTask<String, String, String> {
	    	 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(OnlineRegister.this);
	            pDialog.setMessage("Creating Student..");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }
	 
	        /**
	         * Creating student
	         * */
	        protected String doInBackground(String... args) {
	            String name = inputRollno.getText().toString();
	            String semester = inputSemester.getText().toString();
	            String username = inputUserid.getText().toString();
	            String password = inputPassword.getText().toString();
	            String Branch = branch.getText().toString();
	 
	            // Building Parameters
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("userid", username));
	            params.add(new BasicNameValuePair("branch", Branch));
	            params.add(new BasicNameValuePair("roll_no", name));
	            params.add(new BasicNameValuePair("semester", semester));
	            params.add(new BasicNameValuePair("password", password));
	 
	            // getting JSON Object
	            // Note that create product url accepts POST method
	            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
	                    "POST", params);
	 
	            // check log cat fro response
	            Log.d("Create Response", json.toString());
	 
	            // check for success tag
	            try {
	                int success = json.getInt(TAG_SUCCESS);
	 
	                if (success == 1) {
	                    // successfully created product
	                	OnlineRegister.this.runOnUiThread(new Runnable() {
	                		  public void run() {
	                		    Toast.makeText(OnlineRegister.this, "Registered successfully", Toast.LENGTH_SHORT).show();
	                		  }
	                		});
	                
	                } else {
	                    // failed to create product
	                	OnlineRegister.this.runOnUiThread(new Runnable() {
	                		  public void run() {
	                		    Toast.makeText(OnlineRegister.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
	                		  }
	                		});
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	 
	            return null;
	        }
	 
	        /**
	         * After completing background task Dismiss the progress dialog
	         * **/
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog once done
	            pDialog.dismiss();
	        }
	      
	    }
	}
	 
	 

