package com.engine9;

import java.util.Map;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class JourneyShow extends Activity {
	
	private DrawView cView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_j_show);
		Intent intent = getIntent();
		String iurl = intent.getStringExtra("journeys");
		if(iurl != null){
			Log.e("DEBUG", iurl);
			new DrawRequest().execute(iurl);
		}
		else{
			Log.e("ERROR", "Failed");
		}
		
		cView = (DrawView) findViewById(R.id.show_canvas);
	}
	
	public class DrawRequest extends Request
	{
		@Override
		public void onPostExecute(String result)
		{
			JSONObject json = new JSONObject((Map) JParser.pObject(result));
			try {
				JSONArray jArray = json.getJSONObject("TravelOptions").getJSONArray("Itineraries");
				for(int i = 0; i < jArray.length(); i++){
					JSONObject j = jArray.getJSONObject(i);
					drawRoute(j);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("ERROR", "Bad JSON");
			}
			//Log.e("DEBUG", String.valueOf(json.has("TravelOptions")));
		}
	}
	
	public class DrawView extends View
	{
		public DrawView(Context context) {
	        super(context);            
	    }

	    @Override
	    public void onDraw(Canvas canvas, JSONObject journey) {
	    	
	    }
	}
	
	private void drawRoute(JSONObject journey){
		
	}
}
