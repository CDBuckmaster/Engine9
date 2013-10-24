package engine9.allaboard;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import com.engine9.R;

import android.content.Context;
import android.graphics.Color;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class AbstractAdapter extends ArrayAdapter<AbstractInfo> {

	private Vector<AbstractInfo> stops;
	private Context context;
	
	public Boolean highlightSet = false;
	
	public AbstractAdapter(Context context, Vector<AbstractInfo> stops) {
		super(context, R.layout.list_abstract, stops);
		this.stops = stops;
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return stops.size();
	}


	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	@Override
	public View getView(int position, View convert, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View row = inflater.inflate(R.layout.list_abstract, parent, false);
		
		TextView time = (TextView) row.findViewById(R.id.abstract_time);
		TextView area = (TextView) row.findViewById(R.id.abstract_area);
		area.setTextColor(0xFFFFFFFF);
		
		AbstractInfo current = stops.get(position);
		
		Long t = (current.time - System.currentTimeMillis())/ 60000;
		if(t == 0){
			time.setText("Now");
		}
		else if(t < 0){
			time.setText("Past");
			time.setTextColor(Color.GRAY);
			area.setTextColor(Color.GRAY);
		}
		else if(t > 300){
			Date date = new  Date(current.time);
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
			time.setText(dateFormat.format(date));
		}
		else if(t > 60){
			int hours = (int) (t/60);
			int minutes = (int) (t%60);
			time.setText(String.valueOf(hours) + " hours " + String.valueOf(minutes) + " mins");
		}
		else
		{
			time.setText(String.valueOf(t) + " mins");
		}
		
		
		area.setText(current.description);
		
		Button line = (Button) row.findViewById(R.id.abstract_line);
		if(position == 0){
			if(System.currentTimeMillis() < current.time){
				line.setBackgroundResource(R.drawable.bluedotlinedown);
			}
			else{
				line.setBackgroundResource(R.drawable.greydotlinedown);
			}
		}
		else if(position == stops.size() - 1){
			if(System.currentTimeMillis() < current.time){
				line.setBackgroundResource(R.drawable.bluedotlineup);
			}
			else{
				line.setBackgroundResource(R.drawable.greydotlineup);
			}
		}
		else{
			if(System.currentTimeMillis() < current.time){
				line.setBackgroundResource(R.drawable.bluedotlinemid);
			}
			else{
				line.setBackgroundResource(R.drawable.greydotlinemid);
			}
		}
		//row.setBackgroundColor(Color.RED);
		/*
		Geocoder gcd = new Geocoder(getContext(), Locale.getDefault());
		try {
			area.setText( gcd.getFromLocation(current.position.latitude, 
					current.position.longitude, 1).get(0).getLocality());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		return row;
	}
	

}
