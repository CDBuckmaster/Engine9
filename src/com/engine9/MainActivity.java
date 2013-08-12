package com.engine9;

import com.engine9.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.mapButton);
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(com.engine9.MainActivity.this, com.engine9.MapActivity.class));	
			}
			
		});
		
		Button sButton = (Button) findViewById(R.id.syncButton);
		sButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				new Request().execute("https://opia.api.translink.com.au/v1/travel/rest/plan/LM%3ATrain%20Stations%3ARobina%20station/LM%3ATrain%20Stations%3ASouth%20Brisbane%20station?timeMode=0&at=Mon%2C+19+Aug+2013+06%3A59%3A12+GMT&walkSpeed=2&maximumWalkingDistanceM=1500&api_key=special-key");
				
			}
			
		});
		
	}


}
