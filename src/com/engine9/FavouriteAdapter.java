package com.engine9;

import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

//Converts a Vector of strings into a list of strings
public class FavouriteAdapter extends ArrayAdapter<FavouriteInfo> {
	
	private Context context;
	private Vector<FavouriteInfo> favs;
	
	public FavouriteAdapter(Context context, Vector<FavouriteInfo> favs){
		super(context, R.layout.list_favourite, favs);
		this.context = context;
		this.favs = favs;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return favs.size();
	}


	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 * @param position 
	 */
	@Override
	public View getView(int position, View convert, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View row = inflater.inflate(R.layout.list_favourite, parent, false);
		//row.setFocusable(false);
		final TextView favV = (TextView) row.findViewById(R.id.fav_text);
		favV.setFocusable(false);
		
		Button delB = (Button) row.findViewById(R.id.delete_fav);
		delB.setFocusable(false);
		
		//Set name
		final String code;
		if(favs.size() > 0){
			code = favs.get(position).name;
			favV.setText(code);
		}
		else
		{
			code = "";
		}
		
		//Favourite is deleted when star button is clicked
		delB.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				FavouriteManager.deleteFavourite(code, context);
				clear();
				addAll(FavouriteManager.getFavourites(context));
				notifyDataSetChanged();
				
			}
			
		});
		
		TextView routeV = (TextView) row.findViewById(R.id.fav_route);
		routeV.setText(favs.get(position).route);
		
		//If the favourite has a description, display it, otherwise keep it invisible
		TextView description = (TextView) row.findViewById(R.id.fav_description);
		if(!favs.get(position).description.equals(" ")){
			description.setText(favs.get(position).description);
			description.setVisibility(View.VISIBLE);
		}
		else
		{
			description.setVisibility(View.GONE);
		}
		
		//Set the favourite colour
		Button colourB = (Button) row.findViewById(R.id.fav_colour);
		colourB.setFocusable(false);
		String c = favs.get(position).colour;
		if(c.length() > 1){
			colourB.setVisibility(View.VISIBLE);
			if(c.equals("red")){
				colourB.setBackgroundColor(0xFFF53F3F);
			}
			if(c.equals("orange")){
				colourB.setBackgroundColor(0xFFFD9526);
			}
			if(c.equals("yellow")){
				colourB.setBackgroundColor(0xFFF8E54D);
			}
			if(c.equals("green")){
				colourB.setBackgroundColor(0xFFAEE631);
			}
			if(c.equals("light blue")){
				colourB.setBackgroundColor(0xFF2FCCDC);
			}
			if(c.equals("dark blue")){
				colourB.setBackgroundColor(0xFF2C81CC);
			}
			if(c.equals("purple")){
				colourB.setBackgroundColor(0xFFC968CC);
			}
			
		}
		else{
			colourB.setVisibility(View.INVISIBLE);
		}
		
		
		
		return row;
	}

}
