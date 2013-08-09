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
				new Request().execute("http://www.google.com");
				
			}
			
		});
		
	}


}
