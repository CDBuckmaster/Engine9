package com.engine9;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.Date;

import org.json.JSONObject;

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
	private LinkedHashMap<String, List<String>> timetable = new LinkedHashMap();

	private Date time;
	private DateFormat formatter = new SimpleDateFormat("hh:mm:ss");

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timetable);	
	}

	/**
	 * Add the vehicle timetable to the local device*/
	private void addTimetable(String vehicleID, List<String> time) {
		if (vehicleID == null || time == null || time.size() == 0) {
			throw new NullPointerException();
		}
		timetable.put(vehicleID, time);
	}

	public String toString() {
		return timetable.toString();
	}

	/**
	 * Find the timetable for the vehicle based on stop*/
	private void findStopTimetable(String stopID) {
		if (stopID == null) {
			throw new NullPointerException();
		}

		//Find the vehicle timetable whether in the local store
		if (!timetable.containsKey(stopID)) {
			//Need to search the database can then display (Maybe store locally as well)
		} else {
			timetable.get(stopID);
		}
	}

	/**
	 * Find the timetable for the vehicle based on service, structure same as stop timetable*/
	private void findServiceTimetable(String serviceID) {
		if (serviceID == null) {
			throw new NullPointerException();
		}

		if (!timetable.containsKey(serviceID)) {

		} else {
			timetable.get(serviceID);
		}
	}

	/**
	 * Delete the timetable from local device*/
	private void deleteStopTimetable(String timetableKey) {
		if (timetableKey == null) {
			throw new NullPointerException();
		}
		//Remove the timetable locally
		if (timetable.containsKey(timetableKey)) {
			timetable.remove(timetableKey);
		} 
	}

	/**
	 * Count the time and highlight the service if approaching within 5 mim*/
	private void timeCountDown() {

	}

	/**
	 * Set which type of data will be display*/
	private void dataDisplay() {

	}

	/**
	 * Check the timetable data whether is service realtime or static. If the 
	 * realtime data can be find, use that; show the static data otherwise*/
	private void checkDataType() {

	}

	private class TimeRequest extends Request{
		@Override
		public void onPostExecute(String result) {
			JSONObject jData = new JSONObject((Map) JParser.pObject(result));
		}
	}
}
