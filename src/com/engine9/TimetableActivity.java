package com.engine9;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.engine9.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TimetableActivity extends Activity {
	private String vehicleID;
	/* The local store (save some timetable) allow user to do the quick search, but only 
	 * allow  around 10 places/service (can add the time limit so that if user did not use
	 * for a while, delete that)*/
	//private LinkedHashMap<String, List<String>> timetable = new LinkedHashMap();
	/* The global store (save all timetable) allow user to search, but really depends on
	 * Internet so that it might be slow */
	private JSONObject jData;

	private Date time;
	private DateFormat formatter = new SimpleDateFormat("hh:mm:ss");

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timetable);
		Intent intent = getIntent();
		String iurl = intent.getStringExtra("timeURL");
		new TimeRequest().execute(iurl);
	}

	/**
	 * Add the vehicle timetable to the local device*/
	private void addTimetable(String vehicleID, List<String> time) {
		if (vehicleID == null || time == null || time.size() == 0) {
			throw new NullPointerException();
		}
		try {
			jData.put(vehicleID, time);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		return jData.toString();
	}

	/**
	 * Find the timetable for the vehicle based on stop*/
	private void findStopTimetable(String stopID) {
		if (stopID == null) {
			throw new NullPointerException();
		}

		//Find the vehicle timetable whether in the local store
		if (!jData.has(stopID)) {
			//Need to search the database can then display (Maybe store locally as well)
		} else {
			try {
				jData.get(stopID);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Find the timetable for the vehicle based on service, structure same as stop timetable*/
	private void findServiceTimetable(String serviceID) {
		if (serviceID == null) {
			throw new NullPointerException();
		}

		if (!jData.has(serviceID)) {

		} else {
			try {
				jData.get(serviceID);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Delete the timetable from local device*/
	private void deleteStopTimetable(String timetableKey) {
		if (timetableKey == null) {
			throw new NullPointerException();
		}
		//Remove the timetable locally
		/*if (timetable.containsKey(timetableKey)) {
			timetable.remove(timetableKey);
		} */
	}

	/**
	 * Count the time and highlight the service if approaching within 5 mim*/
	private void timeCountDown() {

	}
	
	private void findTimes(){
		try {
			//Log.e("DEBUG", jData.get("StopTimetables").toString());
			JSONArray st = (JSONArray) jData.get("StopTimetables");
			//JSONObject stop = jData.getJSONArray("StopTimetables").getJSONObject(0).getJSONObject("Stop");
			/*
			JSONArray trips = stop.getJSONArray("Trips");
			for(int i = 0; i < trips.length(); i++){
				JSONObject trip = trips.getJSONObject(i);
				Log.e("Departure", trip.getString("DepartureTime"));
				JSONObject route = trip.getJSONObject("Route");
				Log.e("Code", trip.getString("Code"));
				Log.e("Direction", String.valueOf(trip.getInt("Directions")));
			}*/
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("Error", "You fucked up son");
		}
	}

	private class TimeRequest extends Request{
		@Override
		public void onPostExecute(String result) {
			try {
				jData = JParser2.main(result);
			} catch (Exception e) {
				Log.e("Error", "fuck");
				e.printStackTrace();
			}
			//findTimes();
		}
	}
}
