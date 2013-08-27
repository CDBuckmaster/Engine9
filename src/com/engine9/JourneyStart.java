package com.engine9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * The class to get the journey from the Json file
 * */
public class JourneyStart extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		Button bPlan = (Button) findViewById(R.id.plan_button);
		bPlan.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), JourneyShow.class);
				i.putExtra("journeys", "https://dl.dropboxusercontent.com/u/26635718/journey.json");
				startActivity(i);
			}
			
		});
	}
}
