package com.engine9;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import com.engine9.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;


/***
 * Show the basic map and user can select
 * */
public class MapActivity extends FragmentActivity {

	private GoogleMap mMap;
	private JsonObject jData;
	private Polyline line;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		Intent i = getIntent();
		String iURL = i.getStringExtra("route");
		new MapRequest().execute(iURL);
		
		setUpMap( null);




	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Set initial map and use current location to should on the centre of screen.
	 * 
	 * @param center
	 * 		the user's current location
	 * */
	private void setUpMap(LatLng center) {
		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			if(center != null){
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 15));}
		}
	}
	
	private void addPolyline(){
		for (JsonElement p : jData.get("Paths").getAsJsonArray())
		{
			JsonObject po = p.getAsJsonObject();
			line = mMap.addPolyline(new PolylineOptions()
			.addAll(decodePoly(po.get("Path").getAsString()))
			.width(5)
			.color(Color.RED));
			
		}
	}

	/**
	 * It extends the Request class (which handles getRequests) the onPostExecute 
	 * function is overwritten so that the returned JSON data can be handled 
	 * specifically for this activity (to get User location info)
	 * */
	public class MapRequest extends Request {
		@Override
		public void onPostExecute(String result)
		{
			try{
				jData = (JsonObject) JParser2.main(result);
				addPolyline();
				
			}
			catch(Exception e){
				
				Toast toast = Toast.makeText(getApplicationContext(), "Error receiving request", Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	}
	/*
	 * Decode algorithm by Jeffrey Sambells
	 */
	private List<LatLng> decodePoly(String encoded) {
	    List<LatLng> poly = new ArrayList<LatLng>();
	    int index = 0, len = encoded.length();
	    int lat = 0, lng = 0;

	    while (index < len) {
	        int b, shift = 0, result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lat += dlat;

	        shift = 0;
	        result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lng += dlng;

	        LatLng p = new LatLng((lat/ 1E5),(lng / 1E5));
	        poly.add(p);
	    }

	    return poly;
	}

	
}
