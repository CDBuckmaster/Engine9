package com.engine9;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.io.*;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import com.engine9.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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
	private JsonObject jData;
	private LinkedList times;

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
		
		jData.add(vehicleID, (JsonElement) time);
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
			jData.get(stopID);

		}
	}

	/**
	 * Find the timetable for the vehicle based on service, structure same as stop timetable*/
	private void findServiceTimetable(String serviceID) {
		if (serviceID == null || serviceID.length() == 0) {
			throw new NullPointerException();
		}

		if (!jData.has(serviceID)) {
			/* Send the request to the OPIA API to find the service. If no that
			 * service in API, throw warning and ask user to enter a valid 
			 * service. If the input is invalid, need to throw the warning as 
			 * well*/
		} else {
			jData.get(serviceID);
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
	//Test function (will be modified later) that ouputs all relevant data from JSON file
	private void findTimes(){
		JsonArray st =jData.getAsJsonArray("StopTimetables");
		JsonArray trips = st.get(0).getAsJsonObject().get("Trips").getAsJsonArray();
		for(int i = 0; i < trips.size(); i++){
			JsonObject trip = trips.get(i).getAsJsonObject();
			Log.e("Departure", trip.get("DepartureTime").getAsString());
			JsonObject route = trip.getAsJsonObject("Route");
			Log.e("Code", route.get("Code").getAsString());
			Log.e("Direction", String.valueOf(route.get("Direction").getAsInt()));
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
			findTimes();
		}
	}
	
	private class Listing extends View{

		private long time;
		private String code;
		private String direction;
		
		public Listing(Context context, long _time, String _code, int _direction) {
			super(context);
			time = _time;
			code = _code;
			direction = directionToString(_direction);
		}
		
		private String directionToString(int dir){
			String[] directions = {"North", "South", "East", "West", "Inbound", "Outbound", "Inward", "Outward",
					"Upward", "Downward", "Clockwise", "Counterclockwise", "Direction1", "Direction2", ""};
			return directions[dir];
		}
		
	}
}
