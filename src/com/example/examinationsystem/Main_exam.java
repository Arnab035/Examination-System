package com.example.examinationsystem;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main_exam extends Activity implements OnClickListener {

	private ArrayList<Button> mButtons = new ArrayList<Button>();
	CountDownTimer mCountDownTimer;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_exam);

		Button cb = null;

		ArrayList<Questions> get=getAllQuestions();

		for (int i = 1; i <= get.size(); i++) {

			cb = new Button(this);

			cb.setText(" " + Integer.toString(i));

			cb.setOnClickListener(this);

			cb.setId(i);

			mButtons.add(cb);

		}
		
		
		GridView gridView = (GridView) findViewById(R.id.gridView1);

		gridView.setAdapter(new KeypadAdapter(mButtons));
		TextView tv = (TextView) findViewById(R.id.textView2);
		
		RadioButton radio0 = (RadioButton) findViewById(R.id.radio0);
		RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
		RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
		RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);
		Questions que=new Questions();
		 que= get.get(0);
		   tv.setText(que.getBody());
			radio0.setText(que.getOption1());
			radio1.setText(que.getOption2());
			radio2.setText(que.getOption3());
			radio3.setText(que.getOption4());
			k =0;
			

		final TextView myCounter = (TextView) findViewById(R.id.textView8);
		mCountDownTimer = new CountDownTimer(90000, 1000) {

			@Override
			public void onFinish() {
				timeUp();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				myCounter.setText("Time left: "
						+ String.valueOf(millisUntilFinished / 1000)
						+ " seconds");
			}

		}.start();

	}

	public void timeUp() {

		AlertDialog.Builder builder = new AlertDialog.Builder(Main_exam.this);
		builder.setTitle("Times up!")
				.setMessage(
						"Your exam is over...click ok to know your performance")
				.setCancelable(false)
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								int ab = numberofcorrect();
								int wb = numberofwrong();

								Intent inte = new Intent(Main_exam.this,
										Get_score.class);
								for (int c = 0; c < answerorder.size(); c++)
									answerorder1.add(answerorder.get(c));
								for (int d = 0; d < myoption.size(); d++)
									myoption1.add(myoption.get(d));

								inte.putExtra("CORRECT", ab);
								inte.putExtra("WRONG", wb);
								inte.putIntegerArrayListExtra("ANS",
										answerorder1);
								inte.putStringArrayListExtra("MY", myoption1);
								startActivity(inte);
								finish();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

	int k;

	@Override
	public void onClick(View v) {

		ArrayList<Questions> questions = getAllQuestions();
		Questions que= new Questions();
		
		TextView tv = (TextView) findViewById(R.id.textView2);

		Button selection = (Button) v;

		RadioGroup groupbutton = (RadioGroup) findViewById(R.id.radioGroup1);
		groupbutton.clearCheck();

		RadioButton radio0 = (RadioButton) findViewById(R.id.radio0);
		RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
		RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
		RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);

		int j = selection.getId();
	   que= questions.get(j-1);
	   tv.setText(que.getBody());
		radio0.setText(que.getOption1());
		radio1.setText(que.getOption2());
		radio2.setText(que.getOption3());
		radio3.setText(que.getOption4());
		k = j - 1;
       if(answerorder.indexOf(k)==-1){
		Button bt1 = (Button) findViewById((k + 1));
		bt1.setBackgroundColor(Color.GREEN);
       }

	}

	public ArrayList<String> myoption = new ArrayList<String>();
	public ArrayList<Integer> answerorder = new ArrayList<Integer>();

	public void save_and_next(View view) {

		RadioGroup groupbutton = (RadioGroup) findViewById(R.id.radioGroup1);

		RadioButton radio0 = (RadioButton) findViewById(R.id.radio0);
		RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
		RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
		RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);

		int check = groupbutton.getCheckedRadioButtonId();

		if (check == R.id.radio0) {

			myoption.add("a");
		} else if (check == -1) {
			myoption.add("NULL");
		} else if (check == R.id.radio1) {

			myoption.add("b");

		} else if (check == R.id.radio2) {

			myoption.add("c");
		} else {

			myoption.add("d");
		}

		Toast.makeText(Main_exam.this, "You have saved question " + (k + 1),
				Toast.LENGTH_SHORT).show();
		answerorder.add(k);

		if (check == -1) {
			int a = (k + 1) % 10;
			if (a != 0) {
				Button bt2 = (Button) findViewById(a);
				bt2.setBackgroundColor(Color.BLUE);
			} else {
				Button bt2 = (Button) findViewById(10);
				bt2.setBackgroundColor(Color.BLUE);

			}
		} else {
			int a = (k + 1) % 10;
			if (a != 0) {
				Button bt2 = (Button) findViewById(a);
				bt2.setBackgroundColor(Color.RED);
			} else {
				Button bt2 = (Button) findViewById(10);
				bt2.setBackgroundColor(Color.RED);

			}
		}
		 
		int b = (k + 2) % 10;
	
	  if(answerorder.indexOf((k+1)%10)==-1){
		if (b != 0) {
			Button bt3 = (Button) findViewById(b);

			bt3.setBackgroundColor(Color.GREEN);
		} else {
			Button bt3 = (Button) findViewById(10);

			bt3.setBackgroundColor(Color.GREEN);

		}
	  }

		groupbutton.clearCheck();

		k = (k + 1) % 10;

		ArrayList<Questions> questions = getAllQuestions();
		Questions que= new Questions();
		
		TextView tv = (TextView) findViewById(R.id.textView2);
		
        que= questions.get(k);
		tv.setText(que.getBody());
		radio0.setText(que.getOption1());
		radio1.setText(que.getOption2());
		radio2.setText(que.getOption3());
		radio3.setText(que.getOption4());
	}

	int ab;
	int wb;

	ArrayList<Integer> answerorder1 = new ArrayList<Integer>();
	ArrayList<String> myoption1 = new ArrayList<String>();

	public void markfor_review(View view) {

		RadioGroup groupbutton = (RadioGroup) findViewById(R.id.radioGroup1);
		groupbutton.clearCheck();

		RadioButton radio0 = (RadioButton) findViewById(R.id.radio0);
		RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
		RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
		RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);

		int a = (k + 1) % 10;
		if (a != 0) {
			Button bt2 = (Button) findViewById(a);
			bt2.setBackgroundColor(Color.MAGENTA);
		} else {
			Button bt2 = (Button) findViewById(10);
			bt2.setBackgroundColor(Color.MAGENTA);

		}
		int b = (k + 2) % 10;
		if(answerorder.indexOf((k+1)%10)==-1){
		if (b != 0) {
			Button bt2 = (Button) findViewById(b);
			bt2.setBackgroundColor(Color.GREEN);
		} else {
			Button bt2 = (Button) findViewById(10);
			bt2.setBackgroundColor(Color.GREEN);

		}
		}
		k = (k + 1) % 10;

		ArrayList<Questions> questions = getAllQuestions();
		
		TextView tv = (TextView) findViewById(R.id.textView2);

		Questions que= new Questions();
		que= questions.get(k);
		tv.setText(que.getBody());
		radio0.setText(que.getOption1());
		radio1.setText(que.getOption2());
		radio2.setText(que.getOption3());
		radio3.setText(que.getOption4());
		
	}

	int correct;
	int wrong;

	public int numberofcorrect() {
		ArrayList<Questions> correctoptions = getAllQuestions();
		correct = 0;
		for (int i = 0; i < 10; i++) {
			int b = answerorder.lastIndexOf(i);
			if (b == -1)
				continue;

			else if (correctoptions.get(answerorder.get(b)).getAnswer().equals(
					myoption.get(b)))
				correct++;
		}
		return correct;
	}

	public int numberofwrong() {
		ArrayList<Questions> correctoptions = getAllQuestions();
		wrong = 0;
		for (int i = 0; i < 10; i++) {
			int b = answerorder.lastIndexOf(i);
			if (b == -1)
				continue;

			else if (correctoptions.get(answerorder.get(b)).getAnswer().equals(
					myoption.get(b)))
				correct++;
			else {
				if (myoption.get(b).equals("NULL")) {
					// do nothing
				} else
					wrong++;
			}
		}
		return wrong;
	}

	public ArrayList<Questions> getAllQuestions() {
		ArrayList<Questions> question_no=null;
		
		Questions q=null;

		Activity activity = this;

		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.eee_digitaldesignproper);
		try {
			xpp.next();
			int eventType= xpp.getEventType();
			boolean j=false;
			while(eventType!=XmlPullParser.END_DOCUMENT && !j){
				switch(eventType){
				case XmlPullParser.START_DOCUMENT:
				 question_no = new ArrayList<Questions>();
					break;
				case XmlPullParser.START_TAG:
					String text= xpp.getName();
					if(text.equalsIgnoreCase("question")){
						q=new Questions();// object newly created at this area every time...
					}
					else if(q!=null){
					if(text.equalsIgnoreCase("body")){
						eventType= xpp.next();
						if(eventType==XmlPullParser.TEXT){
							String tv=xpp.getText();
							q.setBody(tv);
						}
						
						
					}
					else if(text.equalsIgnoreCase("option1")){
						eventType= xpp.next();
						if(eventType==XmlPullParser.TEXT){
							String tv=xpp.getText();
							q.setOption1(tv);
					}
					}
					else if(text.equalsIgnoreCase("option2")){
						eventType= xpp.next();
						if(eventType==XmlPullParser.TEXT){
							String tv=xpp.getText();
							q.setOption2(tv);
					}
					}
					else if(text.equalsIgnoreCase("option3")){
						eventType= xpp.next();
						if(eventType==XmlPullParser.TEXT){
							String tv=xpp.getText();
							q.setOption3(tv);
				}
					}
					else if(text.equalsIgnoreCase("option4")){
						eventType= xpp.next();
						if(eventType==XmlPullParser.TEXT){
							String tv=xpp.getText();
							q.setOption4(tv);
			}
					}
					else if(text.equalsIgnoreCase("answer")){
						eventType= xpp.next();
						if(eventType==XmlPullParser.TEXT){
							String tv=xpp.getText();
							q.setAnswer(tv);
		}
					}
					}
					
						break;
					
					
				case XmlPullParser.END_TAG:
					String td= xpp.getName();
					if(td.equalsIgnoreCase("question")){
						question_no.add(q);
					
					}
					else if(td.equals("questions")){
						j=true;
		
					}
					 break;
				}
				eventType = xpp.next();
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

	
	public void Go_to_questionpaper(View view) {
		Intent i = new Intent(Main_exam.this, Question_paper.class);
		startActivity(i);

	}

	public void Go_to_instructions(View view) {
		Intent j = new Intent(Main_exam.this, Instruction_page_together.class);
		startActivity(j);
	}

	public void Submit(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Main_exam.this);
		// set title
		alertDialogBuilder.setTitle("Your exam is over!!!");
		alertDialogBuilder
				.setMessage(
						"Click yes to exit or Click no to return to your exam")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								int ab = numberofcorrect();
								int wb = numberofwrong();

								Intent intent = new Intent(Main_exam.this,
										Get_score.class);
								for (int c = 0; c < answerorder.size(); c++)
									answerorder1.add(answerorder.get(c));
								for (int d = 0; d < myoption.size(); d++)
									myoption1.add(myoption.get(d));

								intent.putExtra("CORRECT", ab);
								intent.putExtra("WRONG", wb);
								intent.putIntegerArrayListExtra("ANS",
										answerorder1);
								intent.putStringArrayListExtra("MY", myoption1);
								startActivity(intent);
								mCountDownTimer.cancel();
								finish();

							}

						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		alertDialog.show();
	}

}
