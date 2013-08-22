package com.engine9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeAdapter extends ArrayAdapter<Listing> {

	private Context context;
	private Listing[] values;
	public TimeAdapter(Context context, Listing[] values) {
		super(context, R.layout.list_timetable, values);
		this.values = values;
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.length;
	}


	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convert, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View row = inflater.inflate(R.layout.list_timetable, parent, false);
		TextView codeV = (TextView) row.findViewById(R.id.code);
		TextView directionV = (TextView) row.findViewById(R.id.direction);
		TextView timeV = (TextView) row.findViewById(R.id.time);
		
		codeV.setText(values[position].code);
		directionV.setText(values[position].direction);
		timeV.setText(String.valueOf(values[position].time));
		
		return row;
	}

}
