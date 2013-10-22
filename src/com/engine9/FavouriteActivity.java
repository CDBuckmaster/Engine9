package com.engine9;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Edit and save the favourite services
 * */
public class FavouriteActivity extends Activity {

	private ListView favList;  //create a list to store the favourite services
	private FavouriteAdapter adapter; 
	private EditText favText;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favourite);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Grouping");
		actionBar.setDisplayHomeAsUpEnabled(false);
		
		//FavouriteManager.deleteAllFavourites(getApplicationContext());
		favList = (ListView) findViewById(R.id.abstract_list);
		adapter = new FavouriteAdapter(getApplicationContext(), 
				FavouriteManager.getFavourites(getApplicationContext()));
		favList.setAdapter(adapter);
		
		favList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				// TODO Auto-generated method stub
				try{
				FavouriteDialog fd = new FavouriteDialog();
				fd.fi = FavouriteManager.getFavourites(getApplicationContext()).get(pos);
				fd.title += fd.fi.name;
				fd.adapter = adapter;
				fd.show(getFragmentManager(), "edit");
				}
				catch(Exception e){
					
				}
				return false;
				
				//doquery(query);
			}
			
		});
		
		//favText = (EditText) findViewById(R.id.fav_text1); 
	}
	
	void doquery(String name, String route){ 
		if(FavouriteManager.inFavourites(getApplicationContext(), name)){
			FavouriteManager.AddFavourite(name,route, getApplicationContext()); 
			adapter.clear();
			adapter.addAll(FavouriteManager.getFavourites(getApplicationContext()));
			adapter.notifyDataSetChanged();
		}
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
	        	startActivity(new Intent(FavouriteActivity.this, StopMapActivity.class));
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/*public void onAddButtonPush(View view) {
		if (!FavouriteManager.inFavourites(getApplicationContext(), favText.getText().toString())) {
			FavouriteManager.AddFavourite(favText.getText().toString(), getApplicationContext());
			adapter.clear();
			adapter.addAll(FavouriteManager.getFavourites(getApplicationContext()));
			adapter.notifyDataSetChanged();
		}
		
	}*/
}
