package com.engine9;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import com.engine9.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * The Abstract map allow use to have a quick view for the next few stops info.
 * It allows user to have a quick check the stop that he/she wants whether it is
 * approaching. 
 * */
public class AbstractActivity extends Activity {
	
	private JsonArray jData;
	private ListView stopsList;
	private AbstractAdapter adapter;
	private Vector<AbstractInfo> stops = new Vector<AbstractInfo>();
	private BroadcastReceiver br;
	private Boolean registered = false;
	private AbstractRequest ar;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abstract);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Current Journey");
		actionBar.setDisplayHomeAsUpEnabled(false);
		
		stopsList = (ListView) findViewById(R.id.abstract_list);
		
		
		Intent i = getIntent();
		final String sURL = i.getStringExtra("stops");
		final String iURL = i.getStringExtra("route");
		ar = new AbstractRequest();
		ar.execute(sURL);
		
		Button aButton = (Button) findViewById(R.id.abstract_to_map_button);
		aButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent ia = new Intent(getApplicationContext(), MapActivity.class);
				ia.putExtra("stops", sURL);
				ia.putExtra("route", iURL);
				
				startActivity(ia);
			}
			
		});
		}
	
	protected void onStop(){
		super.onStop();
		if(br != null && registered){
			registered = false;
			unregisterReceiver(br);
		}
		if(ar != null && ar.getStatus() == AsyncTask.Status.FINISHED){
			ar.cancel(true);
		}
	}
	
	protected void onPause(){
		super.onPause();
		if(br != null && registered){
			registered = false;
			unregisterReceiver(br);
		}
		if(ar != null && ar.getStatus() == AsyncTask.Status.FINISHED){
			ar.cancel(true);
		}
	}
	
	protected void onResume(){
		super.onResume();
		if(br != null && !registered){
			registered = true;
			registerReceiver(br, new IntentFilter(Intent.ACTION_TIME_TICK));
		}
	}
	
	/**
	 * It extends the Request class (which handles getRequests)
	 * the onPostExecute function is overwritten so that the returned JSON
	 * data can be handled specifically for this activity (to get Stop info)
	 * */
	public class AbstractRequest extends Request{
		ProgressDialog dialog;
		@Override
		public void onPreExecute(){
			dialog= ProgressDialog.show(AbstractActivity.this, "Downloading stops","Please wait a moment", true);
		}
		
		@Override
		public void onPostExecute(String result)
		{
			try{
				jData = (JsonArray) JParser2.main(result);
				loadStops();
				
				br =  new BroadcastReceiver(){

					@Override
					public void onReceive(Context context, Intent intent) {
						 if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0) {
							 adapter.highlightSet = false;
							 adapter.notifyDataSetChanged();
						 }
						
					}
					
				};
				
				registerReceiver(br, new IntentFilter(Intent.ACTION_TIME_TICK));
				registered = true;
			}
			catch(Exception e){
				Toast toast = Toast.makeText(getApplicationContext(), "Error receiving request", Toast.LENGTH_SHORT);
				toast.show();
			}
			
			dialog.dismiss();
			
		}
	}
	
	/**
	 * Get the all future stops based on route
	 * */
	private void loadStops(){
		for (JsonElement j : jData){
			JsonObject jo = j.getAsJsonObject();
			
			/* Get stops location information */
			LatLng pos = new LatLng(jo.get("Lat").getAsDouble(), jo.get("Lng").getAsDouble());
			Long t = Long.parseLong(jo.get("time").getAsString().substring(6, 19));
			String des = jo.get("des").getAsString();
			
			AbstractInfo ai = new AbstractInfo(pos, t, des);
			
			stops.add(ai);
		}
		
		Collections.reverse(stops);
		
		adapter = new AbstractAdapter(getApplicationContext(), stops);
		stopsList.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.stop_map_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_map:
	        	startActivity(new Intent(AbstractActivity.this, StopMapActivity.class));
	            return true;
	        case R.id.action_favourite:
	        	startActivity(new Intent(AbstractActivity.this, FavouriteActivity.class));
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
