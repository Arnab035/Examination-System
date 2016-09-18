package com.example.examinationsystem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GetAllStudents extends ListActivity{
   private ProgressDialog pdialog;
   JSONParser jParser = new JSONParser();
   
   ArrayList<HashMap<String, String>> studentsList;

   // url to get all products list
   private static String url_all_students = "http://10.0.2.2/ecs_arnab/get_all_students.php";

   // JSON Node names
   private static final String TAG_SUCCESS = "success";
   private static final String TAG_STUDENTS = "students";
   private static final String TAG_SEMESTER = "semester";
   private static final String TAG_BRANCH = "branch";
   private static final String TAG_ROLLNO = "roll_no";

   // products JSONArray
   JSONArray students = null;

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.getallstudents);

       // Hashmap for ListView
       studentsList = new ArrayList<HashMap<String, String>>();

       // Loading products in Background Thread
       new LoadAllStudents().execute();

       // Get listview
     

}
   
   class LoadAllStudents extends AsyncTask<String, String, String> {
	   
       /**
        * Before starting background thread Show Progress Dialog
        * */
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           pdialog = new ProgressDialog(GetAllStudents.this);
           pdialog.setMessage("Loading students. Please wait...");
           pdialog.setIndeterminate(false);
           pdialog.setCancelable(false);
           pdialog.show();
       }

       /**
        * getting All products from url
        * */
       protected String doInBackground(String... args) {
           // Building Parameters
           List<NameValuePair> params = new ArrayList<NameValuePair>();
           // getting JSON string from URL
           JSONObject json = jParser.makeHttpRequest(url_all_students, "GET", params);

           // Check your log cat for JSON reponse
           Log.d("All Students: ", json.toString());

           try {
               // Checking for SUCCESS TAG
               int success = json.getInt(TAG_SUCCESS);

               if (success == 1) {
                   // products found
                   // Getting Array of Products
                   students = json.getJSONArray(TAG_STUDENTS);

                   // looping through All Products
                   for (int i = 0; i < students.length(); i++) {
                       JSONObject c = students.getJSONObject(i);

                       // Storing each json item in variable
                       String semester = c.getString(TAG_SEMESTER);
                       String branch = c.getString(TAG_BRANCH);
                       String rollno = c.getString(TAG_ROLLNO);
                       

                       // creating new HashMap
                       HashMap<String, String> map = new HashMap<String, String>();

                       // adding each child node to HashMap key => value
                      
                       map.put(TAG_BRANCH, "Branch:  " +branch);
                       map.put(TAG_SEMESTER,"Semester:  " +semester);
                       map.put(TAG_ROLLNO, "Rollno:  " +rollno );

                       // adding HashList to ArrayList
                       studentsList.add(map);
                   }
               } else {
                   // no products found
                   // toast msg
                  Toast.makeText(GetAllStudents.this, "No students found", Toast.LENGTH_SHORT).show();
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
           // dismiss the dialog after getting all products
           pdialog.dismiss();
           // updating UI from Background Thread
           runOnUiThread(new Runnable() {
               public void run() {
                   /**
                    * Updating parsed JSON data into ListView
                    * */
                   ListAdapter adapter = new SimpleAdapter(
                           GetAllStudents.this, studentsList,
                           R.layout.all_students, new String[] { 
                                   TAG_BRANCH,TAG_SEMESTER,TAG_ROLLNO},
                           new int[] { R.id.textView1, R.id.textView2, R.id.textView3 });
                // updating listview
                   setListAdapter(adapter);
               }
           });

       }

   }
}
