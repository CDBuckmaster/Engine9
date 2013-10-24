package engine9.allaboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.engine9.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


 /** 
 * This class is used to translate an array of listing data into an array
 * of Views for a ViewList to use.  In this instance, it is being used
 * for TimeTableActivity
 */
public class TimeAdapter extends ArrayAdapter<Listing> {

	private Context context;
	private ArrayList<Listing> values;

	/**
	 * Basic constructor for the time
	 * 
	 * 		@param context
	 * 			The context the ListView belongs to
	 * 		@param times
	 * 			An array of Listing classes
	 * */
	public TimeAdapter(Context context, ArrayList<Listing> times) {
		super(context, R.layout.list_timetable, times);
		this.values = times;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.size();
	}


	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
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
		
		View row = inflater.inflate(R.layout.list_timetable, parent, false);
		
		TextView codeV = (TextView) row.findViewById(R.id.code);
		codeV.setFocusable(false);
		TextView directionV = (TextView) row.findViewById(R.id.direction);
		directionV.setFocusable(false);
		TextView timeV = (TextView) row.findViewById(R.id.time);
		timeV.setFocusable(false);
		
		final String id = values.get(position).id;
		final int type = values.get(position).type;
		final String code = values.get(position).code;
		final String route = values.get(position).route;
		codeV.setText(code);
		
		directionV.setText(route);
		Long time = (values.get(position).time * 10  - System.currentTimeMillis())/ 60000;
		if(time == 0){
			timeV.setText("Now");
		}
		else if(time < 0){
			timeV.setText("Due");
		}
		else if(time > 300){
			Date date = new  Date(values.get(position).time * 10);
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
			timeV.setText(dateFormat.format(date));
		}
		else if(time > 60){
			int hours = (int) (time/60);
			int minutes = (int) (time%60);
			timeV.setText(String.valueOf(hours) + " hours " + String.valueOf(minutes) + " mins");
		}
		else
		{
			timeV.setText(String.valueOf(time) + " mins");
		}
		
		
		final Button favButton  = (Button) row.findViewById(R.id.add_fav_button);
		favButton.setFocusable(false);
		if(FavouriteManager.inFavourites(context, code)){
			favButton.setBackgroundResource(R.drawable.starfull);
		}
		else{
			favButton.setBackgroundResource(R.drawable.staroutline);
		}
		
		favButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				if(!FavouriteManager.inFavourites(context, code)){
					FavouriteManager.AddFavourite(code, route, context);
					favButton.setBackgroundResource(R.drawable.starfull);
					notifyDataSetChanged();
					
				}
				else{
					FavouriteManager.deleteFavourite(code, context);
					favButton.setBackgroundResource(R.drawable.staroutline);
					notifyDataSetChanged();
				}
				
			}
			
		});
		
		final Button colour = (Button) row.findViewById(R.id.time_colour);
		colour.setFocusable(false);
		String c = FavouriteManager.getColour(context, code);
		if(c.length() > 1){
			colour.setVisibility(View.VISIBLE);
			if(c.equals("red")){
				colour.setBackgroundColor(0xFFF53F3F);
			}
			if(c.equals("orange")){
				colour.setBackgroundColor(0xFFFD9526);
			}
			if(c.equals("yellow")){
				colour.setBackgroundColor(0xFFF8E54D);
			}
			if(c.equals("green")){
				colour.setBackgroundColor(0xFFAEE631);
			}
			if(c.equals("light blue")){
				colour.setBackgroundColor(0xFF2FCCDC);
			}
			if(c.equals("dark blue")){
				colour.setBackgroundColor(0xFF2C81CC);
			}
			if(c.equals("purple")){
				colour.setBackgroundColor(0xFFC968CC);
			}
			
		}
		else{
			colour.setVisibility(View.INVISIBLE);
		}
		
		/*Button mapButton = (Button) row.findViewById(R.id.to_map_button);
		mapButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				Intent i = new Intent(view.getContext(), MapActivity.class);
				i.putExtra("route", "http://deco3801-005.uqcloud.net/cache/network/rest/route-map-path/?route=" + code + "&type=" + type);
				i.putExtra("stops", "http://deco3801-005.uqcloud.net/stops-from-tripID/?tripID=" + id);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				view.getContext().startActivity(i);
			}
		});*/
		return row;
	}
	

}
