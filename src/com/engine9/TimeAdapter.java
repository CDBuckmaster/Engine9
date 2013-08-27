package com.engine9;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


 /** 
 * This class is used to translate an array of listing data into an array
 * of Views for a ViewList to use.  In this instance, it is being used
 * for TimeTableActivity
 */
public class TimeAdapter extends ArrayAdapter<Listing> {

	private Context context;
	private ArrayList<Listing> values;
	

	/**
	 * Basic constructor for the time
	 * 
	 * 		@param context
	 * 			The context the ListView belongs to
	 * 		@param times
	 * 			An array of Listing classes
	 * */
	public TimeAdapter(Context context, ArrayList<Listing> times) {
		super(context, R.layout.list_timetable, times);
		this.values = times;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.size();
	}


	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 * @param position 
	 */
	@Override
	public View getView(int position, View convert, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View row = inflater.inflate(R.layout.list_timetable, parent, false);
		TextView codeV = (TextView) row.findViewById(R.id.code);
		TextView directionV = (TextView) row.findViewById(R.id.direction);
		TextView timeV = (TextView) row.findViewById(R.id.time);
		codeV.setText(values.get(position).code);
		directionV.setText(values.get(position).direction);
		timeV.setText(String.valueOf((values.get(position).time - System.currentTimeMillis()/10)/ 60000));
		Log.e("DEBUG", String.valueOf(System.currentTimeMillis()/10 + " " + values.get(position).time));
		return row;
	}

}
