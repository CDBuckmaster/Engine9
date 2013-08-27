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
import android.widget.ListView;
import android.widget.TextView;

public class TimetableActivity extends Activity {
	private String vehicleID;
	/* The local store (save some timetable) allow user to do the quick search, but only 
	 * allow  around 10 places/service (can add the time limit so that if user did not use
	 * for a while, delete that)*/
	//private LinkedHashMap<String, List<String>> timetable = new LinkedHashMap();
	/* The global store (save all timetable) allow user to search, but really depends on
	 * Internet so that it might be slow */
	private JsonObject jData;
	private LinkedList<Listing> times = new LinkedList<Listing>();

	private Date time;
	private DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
	private ListView timeList;
	
	//Temporary favourites array
	private String[] favourites = {"412", "411"};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timetable);
		Intent intent = getIntent();
		String iurl = intent.getStringExtra("timeURL");
		new TimeRequest().execute(iurl);
		
		timeList = (ListView) findViewById(R.id.list_view);
		
		Button favButton = (Button)findViewById(R.id.fav_button);
		favButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				
			}
			
		});
	}

	/**
	 * Add and store the vehicle timetable to the local device
	 * 	   @throws InvalidPointerException 
	 * 	        if the vehicleID is invalid
	 *     @throws NullPointerException
	 * 	        if the vehicleID or time is empty or null*/
	private void addTimetable(String vehicleID, List<String> time) 
			throws InvalidPointerException {
		if (vehicleID == null || vehicleID.length() == 0 || time == null || time.size() == 0) {
			throw new NullPointerException();
		}
		if (vehicleID.length() > 4) {
			throw new InvalidPointerException();
		}
		
		jData.add(vehicleID, (JsonElement) time);
	}

	public String toString() {
		return jData.toString();
	}

	/**
	 * Find the timetable for the vehicle based on stop
	 * @throws InvalidPointerException */
	private void findStopTimetable(String stopID) throws InvalidPointerException {
		if (stopID == null || stopID.length() == 0) {
			throw new NullPointerException();
		}
		if (stopID.length() != 6 ) {
			throw new InvalidPointerException();
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
	 * Delete the timetable from local device
	 *     @throws NullPointerException
	 * 	       if timetableKey is null or empty
	 *     @throws InvalidPointerException
	 * 	       if timetableKey is invalid*/
	private void deleteStopTimetable(String timetableKey) {
		if (timetableKey == null || timetableKey.length() == 0) {
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
	
	
	//Test function (will be modified later) that outputs all relevant data from JSON file
	private void findTimes() {
		JsonArray st =jData.getAsJsonArray("StopTimetables"); //Get the Stop info
		//Get the particular trip info
		JsonArray trips = st.get(0).getAsJsonObject().get("Trips").getAsJsonArray(); 
		
		//Loop all the elements and get each single trip info
		for(int i = 0; i < trips.size(); i++){
			JsonObject trip = trips.get(i).getAsJsonObject();
			
			JsonObject route = trip.getAsJsonObject("Route");
			
			//Use the long type to store the departure time with its UTC time zone
			long d = Long.parseLong(trip.get("DepartureTime").getAsString().substring(6, 19)) + 1000;
			//Get single service info and add to the list
			Listing l = new Listing(d, route.get("Code").getAsString(),  route.get("Direction").getAsInt());
			times.add(l);
			
		}
		
		TimeAdapter adapter = new TimeAdapter(getApplicationContext(), 
				times.toArray(new Listing[times.size()]));
		timeList.setAdapter(adapter);
	}
	

	/**
	 * It extends the Request class (which handles getRequests)
	 * the onPostExecute function is overwritten so that the returned JSON
	 * data can be handled specifically for this activity (to get Time info)
	 * */
	private class TimeRequest extends Request{
		@Override
		public void onPostExecute(String result) {
			try {
				jData = JParser2.main(result);
			} catch (Exception e) {
				Log.e("Error", "Parsing error");
				e.printStackTrace();
			}
			findTimes();
		}
	}
	
}
