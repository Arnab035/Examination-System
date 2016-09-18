package com.example.examinationsystem;





import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;




public class Question_paper extends Activity{
	private String[] lv_arr= {};
		 
		    /** Called when the activity is first created. */
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.question_paper);
		     
		       ArrayList<String> questions= getAllXML();
		       ListView lv = (ListView) findViewById(R.id.listView01);
		       lv.setClickable(true);
		      
		              
		            	   
		               

		      
		        

		          
		          
		       
		       lv_arr = (String[]) questions.toArray(new String[0]);
		      	        lv.setAdapter(new ArrayAdapter<String>(Question_paper.this,
		       	                android.R.layout.simple_list_item_1, lv_arr));
		       	 
		       	    }
		   
            
		      
		    public ArrayList<String> getAllXML(){
		    	 ArrayList<String> question_no = null;

		          Activity activity = this;
		          

		          Resources res = activity.getResources();
		          XmlResourceParser xpp = res.getXml(R.xml.eee_digitaldesignproper);
		          try {
		              xpp.next();
		              int eventType = xpp.getEventType();
		              
		              while (eventType != XmlPullParser.END_DOCUMENT)
		              {
		               switch(eventType){
		               case XmlPullParser.START_DOCUMENT:
		            	   question_no=new ArrayList<String>();
		            	   break;
		               case XmlPullParser.START_TAG:
							String text= xpp.getName();
							
							
							if(text.equalsIgnoreCase("body")){
								eventType= xpp.next();
								if(eventType==XmlPullParser.TEXT){
									String tv=xpp.getText();
								    question_no.add(tv);
								}
								
								
							}
							else if(text.equalsIgnoreCase("option1")){
								eventType= xpp.next();
								if(eventType==XmlPullParser.TEXT){
									String tv=xpp.getText();
									question_no.add(tv);
							}
							}
							else if(text.equalsIgnoreCase("option2")){
								eventType= xpp.next();
								if(eventType==XmlPullParser.TEXT){
									String tv=xpp.getText();
									question_no.add(tv);
							}
							}
							else if(text.equalsIgnoreCase("option3")){
								eventType= xpp.next();
								if(eventType==XmlPullParser.TEXT){
									String tv=xpp.getText();
									question_no.add(tv);
						}
							}
							else if(text.equalsIgnoreCase("option4")){
								eventType= xpp.next();
								if(eventType==XmlPullParser.TEXT){
									String tv=xpp.getText();
									question_no.add(tv);
					}
							}
							break;
							
							
						case XmlPullParser.END_TAG:
							break;
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

		  

		       
		    }
		 
	
	
