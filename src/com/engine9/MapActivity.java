package com.engine9;

import java.io.InputStream;
import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONObject;

import com.engine9.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;


/***
 * Show the basic map and user can select
 * */
public class MapActivity extends FragmentActivity {

	private GoogleMap mMap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		Intent i = getIntent();
		i.getStringExtra("route");
		
		
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

	/**
	 * It extends the Request class (which handles getRequests) the onPostExecute 
	 * function is overwritten so that the returned JSON data can be handled 
	 * specifically for this activity (to get User location info)
	 * */
	public class MapRequest extends Request {
		@Override
		public void onPostExecute(String result)
		{
			
		}
	}

	
}
