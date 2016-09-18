package com.example.examinationsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Performance_analysis extends Activity {
	
	
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performance_analysis);
      
        ArrayList<String> question= new ArrayList<String>();
        
        question= getAllQuestions();
        
        ArrayList<String> correct= new ArrayList<String>();
        correct= getAllCorrect();
        Intent in=getIntent();
        ArrayList<Integer> answerorder= in.getIntegerArrayListExtra("ANS!");
        ArrayList<String> myoption= in.getStringArrayListExtra("MY!");
        ArrayList<String> orderoption= new ArrayList<String>();
       
               ArrayList<HashMap<String,String>> perform= new ArrayList<HashMap<String,String>>();
        
        for (int j=0; j<10; j++){
        	int b= answerorder.lastIndexOf(j);
        	if(b==-1)orderoption.add(" unknown : You have not saved this answer");
        	else{
        		String ab= myoption.get(b);
        		if(ab.equals("NULL"))orderoption.add(" unknown : You have not selected any option for this answer");
        		else orderoption.add(ab);
        	}
    		
    	}
        for (int i = 0; i < question.size(); i++) {
    		HashMap<String, String> test = new HashMap<String, String>();
    		test.put("question",question.get(i) );
    		test.put("correctanswer", "Correct answer: option "+ correct.get(i));
    		test.put("myanswer", "Your answer: option "+ orderoption.get(i));
    		 perform.add(test);
        }
       
        SimpleAdapter sa = new SimpleAdapter(
        		getApplicationContext(),
        		perform,
        		R.layout.custom_performance,
        		new String[] { "question", "correctanswer",	"myanswer" },
        		new int[] { R.id.textView1, R.id.textView2, R.id.textView3 }) {
        	};
        	
        	ListView mainListView = (ListView) findViewById(R.id.list);
        	mainListView.setAdapter(sa);
        }
    
    public ArrayList<String> getAllQuestions(){
   	 ArrayList<String> question_no = new ArrayList<String>();
   	 
   	
        Activity activity = this;
        

        Resources res = activity.getResources();
        XmlResourceParser xpp = res.getXml(R.xml.eee_digitaldesignproper);
        try {
            xpp.next();
            int eventType = xpp.getEventType();
            
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
           	 if(eventType == XmlPullParser.START_DOCUMENT)
                {

                    eventType = xpp.next();
                } 
                 else if(eventType == XmlPullParser.START_TAG){
                 	String mr = xpp.getName();
                 	if(mr.equals ("body"))
                 			{
                 		eventType = xpp.next();
                 		if(eventType == XmlPullParser.TEXT){
                 			question_no.add(xpp.getText());
                 		}
                 	}
                 }
           	 eventType= xpp.next();
            }
   	
   	
        
        

   } catch (XmlPullParserException e) {
         e.printStackTrace();
         System.out.println("You are very wrong");
   } catch (IOException e) {
         e.printStackTrace();
         System.out.println("What is this");
   }
   return question_no;
   }


    public ArrayList<String> getAllCorrect(){
   	 ArrayList<String> correct = new ArrayList<String>();
   	 
   	
      Activity activity = this;
      

      Resources res = activity.getResources();
      XmlResourceParser xpp = res.getXml(R.xml.eee_digitaldesignproper);
      try {
          xpp.next();
          int eventType = xpp.getEventType();
          
          while (eventType != XmlPullParser.END_DOCUMENT)
          {
         	 if(eventType == XmlPullParser.START_DOCUMENT)
              {

                  eventType = xpp.next();
              } 
               else if(eventType == XmlPullParser.START_TAG){
               	String mr = xpp.getName();
               	if(mr.equals ("answer"))
               			{
               		eventType = xpp.next();
               		if(eventType == XmlPullParser.TEXT){
               			correct.add(xpp.getText());
               		}
               	}
               }
         	 eventType= xpp.next();
          }
   	
   	
      
      

   } catch (XmlPullParserException e) {
       e.printStackTrace();
       System.out.println("You are very wrong");
   } catch (IOException e) {
       e.printStackTrace();
       System.out.println("What is this");
   }
   return correct;
   }



}

