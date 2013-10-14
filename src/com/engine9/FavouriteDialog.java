package com.engine9;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class FavouriteDialog extends DialogFragment {

	public String title = "Service: ";
	public FavouriteInfo fi;
	public String colour = " ";
	public FavouriteAdapter adapter;
	private Button selectedButton;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		final AlertDialog thisDialog;
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.dialog_favourite, null);
		
		final Button red = (Button) v.findViewById(R.id.red_button);
		
		final Button orange = (Button) v.findViewById(R.id.orange_button);
		
		final Button yellow = (Button) v.findViewById(R.id.yellow_button);
		
		final Button green = (Button) v.findViewById(R.id.green_button);
		
		final Button lBlue = (Button) v.findViewById(R.id.light_blue_button);
		
		final Button dBlue = (Button) v.findViewById(R.id.dark_blue_button);
		
		final Button purple = (Button) v.findViewById(R.id.purple_button);
		
		red.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(!colour.equals("red")){
					colour = "red";
					android.view.ViewGroup.LayoutParams params = red.getLayoutParams();
					params.height = dpToPixels(60);
					red.setLayoutParams(params);
					
					if(selectedButton != null){
						android.view.ViewGroup.LayoutParams p =	selectedButton.getLayoutParams();
						p.height = dpToPixels(48);
						selectedButton.setLayoutParams(p);
					}
					selectedButton = red;
				}
				else{
					colour = "";
					android.view.ViewGroup.LayoutParams pa =	selectedButton.getLayoutParams();
					pa.height = dpToPixels(48);
					selectedButton.setLayoutParams(pa);
					selectedButton = null;
				}
				
			}
			
		});
		
		orange.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(!colour.equals("orange")){
					colour = "orange";
					android.view.ViewGroup.LayoutParams params = orange.getLayoutParams();
					params.height = dpToPixels(60);
					orange.setLayoutParams(params);
					
					if(selectedButton != null){
						android.view.ViewGroup.LayoutParams p =	selectedButton.getLayoutParams();
						p.height = dpToPixels(48);
						selectedButton.setLayoutParams(p);
					}
					selectedButton = orange;
				}
				else{
					colour = "";
					android.view.ViewGroup.LayoutParams pa =	selectedButton.getLayoutParams();
					pa.height = dpToPixels(48);
					selectedButton.setLayoutParams(pa);
					selectedButton = null;
				}
				
			}
			
		});
		
		yellow.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(!colour.equals("yellow")){
					colour = "yellow";
					android.view.ViewGroup.LayoutParams params = yellow.getLayoutParams();
					params.height = dpToPixels(60);
					yellow.setLayoutParams(params);
					
					if(selectedButton != null){
						android.view.ViewGroup.LayoutParams p =	selectedButton.getLayoutParams();
						p.height = dpToPixels(48);
						selectedButton.setLayoutParams(p);
					}
					selectedButton = yellow;
				}
				else{
					colour = "";
					android.view.ViewGroup.LayoutParams pa =	selectedButton.getLayoutParams();
					pa.height = dpToPixels(48);
					selectedButton.setLayoutParams(pa);
					selectedButton = null;
				}
			}
			
		});
		
		green.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(!colour.equals("green")){
					colour = "green";
					android.view.ViewGroup.LayoutParams params = green.getLayoutParams();
					params.height = dpToPixels(60);
					green.setLayoutParams(params);
					
					if(selectedButton != null){
						android.view.ViewGroup.LayoutParams p =	selectedButton.getLayoutParams();
						p.height = dpToPixels(48);
						selectedButton.setLayoutParams(p);
					}
					selectedButton = green;
				}
				else{
					colour = "";
					android.view.ViewGroup.LayoutParams pa =	selectedButton.getLayoutParams();
					pa.height = dpToPixels(48);
					selectedButton.setLayoutParams(pa);
					selectedButton = null;
				}
			}
			
		});
		
		lBlue.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(!colour.equals("light blue")){
					colour = "light blue";
					android.view.ViewGroup.LayoutParams params = lBlue.getLayoutParams();
					params.height = dpToPixels(60);
					lBlue.setLayoutParams(params);
					
					if(selectedButton != null){
						android.view.ViewGroup.LayoutParams p =	selectedButton.getLayoutParams();
						p.height = dpToPixels(48);
						selectedButton.setLayoutParams(p);
					}
					selectedButton = lBlue;
				}
				else{
					colour = " ";
					android.view.ViewGroup.LayoutParams pa =	selectedButton.getLayoutParams();
					pa.height = dpToPixels(48);
					selectedButton.setLayoutParams(pa);
					selectedButton = null;
				}
			}
			
		});
		
		dBlue.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(!colour.equals("dark blue")){
					colour = "dark blue";
					android.view.ViewGroup.LayoutParams params = dBlue.getLayoutParams();
					params.height = dpToPixels(60);
					dBlue.setLayoutParams(params);
					
					if(selectedButton != null){
						android.view.ViewGroup.LayoutParams p =	selectedButton.getLayoutParams();
						p.height = dpToPixels(48);
						selectedButton.setLayoutParams(p);
					}
					selectedButton = dBlue;
				}
				else{
					colour = "";
					android.view.ViewGroup.LayoutParams pa =	selectedButton.getLayoutParams();
					pa.height = dpToPixels(48);
					selectedButton.setLayoutParams(pa);
					selectedButton = null;
				}
			}
			
		});
		
		purple.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(!colour.equals("purple")){
					colour = "purple";
					android.view.ViewGroup.LayoutParams params = purple.getLayoutParams();
					params.height = dpToPixels(60);
					purple.setLayoutParams(params);
					
					if(selectedButton != null){
						android.view.ViewGroup.LayoutParams p =	selectedButton.getLayoutParams();
						p.height = dpToPixels(48);
						selectedButton.setLayoutParams(p);
					}
					selectedButton = purple;
				}
				else{
					colour = "";
					android.view.ViewGroup.LayoutParams pa =	selectedButton.getLayoutParams();
					pa.height = dpToPixels(48);
					selectedButton.setLayoutParams(pa);
					selectedButton = null;
				}
			}
			
		});
		
		final TextView description = (TextView) v.findViewById(R.id.description_text);
		description.setText(fi.description);
		
		thisDialog =  new AlertDialog.Builder(getActivity()).setTitle(title)
				.setView(v)
               .setPositiveButton(R.string.pos, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   FavouriteManager.setColour(getActivity(), fi.name, colour);
                       if(description.getText().toString().indexOf("|") == -1 &&
                    		    description.getText().toString().indexOf(":") == -1)
                       {
                    	   FavouriteManager.setDescription(getActivity(), fi.name, description.getText().toString());
                       }
                       adapter.clear();
                       adapter.addAll(FavouriteManager.getFavourites(getActivity()));
                       adapter.notifyDataSetChanged();
                       
                	   
                   }
               })
               .setNegativeButton(R.string.neg, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                	   
                   }
               }).create();
		//thisDialog.show();
        // Create the AlertDialog object and return it
        return thisDialog;
    }
	
	private int dpToPixels(int dip){
		final float scale = getActivity().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}
}
