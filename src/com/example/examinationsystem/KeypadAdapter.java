package com.example.examinationsystem;



import java.util.ArrayList;



import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;




public class KeypadAdapter extends BaseAdapter {
	private ArrayList<Button> mButtons = null;

	public KeypadAdapter(ArrayList<Button> b)

	{

	mButtons = b;

	}

	@Override

	public int getCount() {

	return mButtons.size();

	}

	@Override

	public Object getItem(int position) {

	return (Object) mButtons.get(position);

	}

	@Override

	public long getItemId(int position) {

	return position;

	}
	@Override

	public View getView(int position, View convertView, ViewGroup parent) {

	Button button;

	if (convertView == null) {

	button = mButtons.get(position);

	} else {

	button = (Button) convertView;

	}

	return button;

	}

	}
		
	 