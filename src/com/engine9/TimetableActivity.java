package com.engine9;

import java.util.LinkedHashMap;
import java.util.List;

import com.engine9.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TimetableActivity extends Activity {
	private String vehicleID;
	LinkedHashMap<String, List<String>> timetable = new LinkedHashMap();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timetable);	
	}
	
	private void addTimetable(String vehicleID, List<String> time) {
		if (vehicleID == null || time == null || time.size() == 0) {
			throw new NullPointerException();
		}
		timetable.put(vehicleID, time);
	}
	
	public String toString() {
		return timetable.toString();
	}
	
	private void findTimetable(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		
		//Find the vehicle timetable whether in the local store
		if (!timetable.containsKey(s)) {
			//Need to search the database can then display (Maybe store locally as well)
		} else {
			timetable.get(s);
		}
	}
	
	/***/
	private void deleteTimetable(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		//Remove the timetable locally
		if (timetable.containsKey(s)) {
			timetable.remove(s);
		} 
	}
}
