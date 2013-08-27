package com.engine9;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import android.app.Dialog;
import android.content.Context;
import android.content.IntentSender;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;


public class StopMapActivity extends FragmentActivity implements
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener, LocationListener{

	private GoogleMap mMap;
	private LocationClient mLocationClient;
	private Location currentLocation;
	private LocationManager mLocationManager;
	private JsonObject jData;
	private Vector<Stop> stopVector = new Vector<Stop>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stop_map);
		
		if(!servicesConnected()){
			Toast.makeText(this, "No GooglePlayServices", Toast.LENGTH_SHORT).show();
			mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	        mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(new Criteria(), true), 0, 0, this);

	        currentLocation = mLocationManager.getLastKnownLocation(mLocationManager.getBestProvider(new Criteria(), true));
	        if(currentLocation != null && currentLocation.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
	        	
	        	Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
				//Converts location to address string
				String address;
				try {
					address = gcd.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1).get(0).toString();
					//new StopRequest().execute()
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				mLocationManager.removeUpdates(this);
	        }
		}
		
		setUpMap( null);
	}
	/*
	@Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mLocationClient.connect();
    }
	
	@Override
    protected void onStop() {
		mLocationClient.disconnect();
        super.onStart();
        // Connect the client.
        mLocationClient.connect();
    }*/
	
	
	
	private void setUpMap(LatLng center) {
        if (mMap == null) {
        	mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        	mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        	if(center != null){
        		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 15));}
        	mMap.setOnMarkerClickListener(new OnMarkerClickListener(){

				@Override
				public boolean onMarkerClick(Marker m) {
					for(Stop s : stopVector){
						if(s.markerId == m.getId()){
							//Open TimeTableActivity with new intent
							return true;
						}
					}
					return false;
				}
        		
        	});
        	
        }
	}
	
	//Detects if Google Play Services is available
	private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.
                        isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates",
                    "Google Play services is available.");
            // Continue
            return true;
        // Google Play services was not available for some reason
        } else {
        	 Log.d("Location Updates",
                     "Google Play services is unavailable.");
            }
            return false;
        }

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        9000);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
        	Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            Log.e("Error", String.valueOf(connectionResult.getErrorCode()));
        }
		
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		 Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onDisconnected() {
		Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onLocationChanged(Location location) {
		if(location != null && currentLocation == null){
			Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
			//Converts location to address string
			String address;
			try {
				address = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0).toString();
				//new StopRequest().execute()
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			currentLocation = location;
			mLocationManager.removeUpdates(this);
		}
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Function that converts JsonObject (jData) into a vector (stopVector)
	 * @param j A JsonObject containing stop information
	 */
	private void JsonToVector(JsonObject j){
		
	}
	
	private void addStopsToMap(){
		for(Stop s : stopVector){
			Marker marker = mMap.addMarker(new MarkerOptions()
					.position(new LatLng(s.lat, s.lon))
					.title(s.address));
			s.markerId = marker.getId();
		}
	}
	
	private class StopRequest extends Request{
		@Override
		public void onPostExecute(String result) {
			try {
				jData = JParser2.main(result);
				JsonToVector(jData);
				
			} catch (Exception e) {
				Log.e("Error", "Parsing error");
				e.printStackTrace();
			}
		}
	}
	
	
	private class Stop{
		String markerId;
		Double lat;
		Double lon;
		String stopId;
		String address;
		
		public Stop(String stopId, Double lat, Double lon, String address){
			this.stopId = stopId;
			this.lat = lat;
			this.lon = lon;
			this.address = address;
		}
	}
}

