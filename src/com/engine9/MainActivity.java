package com.engine9;

import com.engine9.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * The main page to control the application
 * */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}
	
	public void timeButtonPush(View view){
		Intent i = new Intent(com.engine9.MainActivity.this, com.engine9.StopMapActivity.class);
		TextView tv = (TextView) findViewById(R.id.time_text);
		i.putExtra("location", tv.getText().toString());
		startActivity(i);
	}
	
	public void mapButtonPush(View view){
		startActivity(new Intent(com.engine9.MainActivity.this, com.engine9.MapActivity.class));
	}
	
	public void abstractButtonPush(View view){
		startActivity(new Intent(com.engine9.MainActivity.this, com.engine9.AbstractActivity.class));
	}

	public void favButtonPush(View view) {
		startActivity(new Intent(com.engine9.MainActivity.this, com.engine9.FavouriteActivity.class));
	}

}
