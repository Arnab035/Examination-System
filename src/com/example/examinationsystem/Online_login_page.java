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

public class Online_login_page extends Activity {
	Button bt1;
	private ProgressDialog pdialog;
	 JSONParser jsonParser = new JSONParser();
	   
	    EditText inputUserid;
	    EditText inputPassword;
	    private static String url_check_data = "http://10.0.2.2/ecs_arnab/check_data.php";
	    
	    // JSON Node names
	    private static final String TAG_SUCCESS = "success";
	 
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.online_login_page);
	        
	        
	        inputUserid = (EditText) findViewById(R.id.editText1);
	        inputPassword = (EditText) findViewById(R.id.editText2);
	     // Create button
	        Button btnCheckStudent = (Button) findViewById(R.id.button1);
	 
	        // button click event
	        btnCheckStudent.setOnClickListener(new View.OnClickListener() {
	 
	            @Override
	            public void onClick(View view) {
	                // creating new product in background thread
	                new CheckStudent().execute();
	            }
	        });
	    }
	 
	  class CheckStudent extends AsyncTask<String, String, String> {
	    	 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pdialog = new ProgressDialog(Online_login_page.this);
	            pdialog.setMessage("Checking Student..");
	            pdialog.setIndeterminate(false);
	            pdialog.setCancelable(true);
	            pdialog.show();
	        }
	 
	        /**
	         * Checking student
	         * */
	        protected String doInBackground(String... args) {
	           
	            String userid = inputUserid.getText().toString();
	            String password = inputPassword.getText().toString();
	 
	            // Building Parameters
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	           
	            params.add(new BasicNameValuePair("userid", userid));
	            params.add(new BasicNameValuePair("password", password));
	 
	            // getting JSON Object
	            // Note that create product url accepts POST method
	            JSONObject json = jsonParser.makeHttpRequest(url_check_data,
	                    "GET", params);
	 
	            // check log cat fro response
	            Log.d("Create Response", json.toString());
	 
	            // check for success tag
	            try {
	                int success = json.getInt(TAG_SUCCESS);
	 
	                if (success == 1) {
	                    // successfully checked student
	                	Online_login_page.this.runOnUiThread(new Runnable() {
	                		  public void run() {
	                		    Toast.makeText(Online_login_page.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
	                		    Intent i=new Intent(Online_login_page.this,Instruction_page.class);
	                		    startActivity(i);
	                		  }
	                		});
	                
	                } else {
	                    // failed to create product
	                	Online_login_page.this.runOnUiThread(new Runnable() {
	                		  public void run() {
	                		    Toast.makeText(Online_login_page.this, "Login unsuccessful...username or password invalid", Toast.LENGTH_SHORT).show();
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
	            pdialog.dismiss();
	        }
	 
	    }
	
	 
	 
	 
	 
	 
	        
	       
	 
	        // view products click event
	      
	 
	            
	           
	 
	      
	            
	            public void register(View view) {
	                // Launching create new product activity
	                Intent i = new Intent(getApplicationContext(), OnlineRegister.class);
	                startActivity(i);
	 
	            }
	       
	    
	}


