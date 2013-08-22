package com.engine9;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.engine9.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

class GroupActivity extends Activity {
	
	private LinkedHashMap<String, List<String>> destination =
			new LinkedHashMap();
	private LinkedHashMap<String, List<String>> destinationGroup = new
			LinkedHashMap();
	
	private String s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		}
	
	public String toString() {
		return "0";
	}
	
	/**
	 * Check whether has bus to particular place, return true if there is at 
	 * least one public transport; return false otherwise.*/
	public boolean hasRoute(String s) {
		if (destination.get(s) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Add destination in to the group*/
	public void addDistination(String s, List<String> ls) {
		//Check the new destination is valid or not
		if (s == null || ls == null || ls.size() == 0 || ls.isEmpty()) {
			throw new NullPointerException("No bus in this suburb, please " +
					"check the public transport route or add new place instead\n");
		}
		//If there is no that destination in the collection, add it
		if (!destination.containsKey(s)) {
			destination.put(s, ls);
		}
	}
	
	/**
	 * Delete destination from the group*/
	public void deleteDistination(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		if (destination.containsKey(s)) {
			destination.remove(s);
		}
	}
	
	/**
	 * Select several destination and make as a group*/
	public void groupDestination(String s, LinkedHashMap<String, List<String>> destination) {
		if (s == null || destination == null || destination.size() == 0) {
			throw new NullPointerException();
		}
		destinationGroup.putAll(destination);
	}
	
	/**
	 * Remove a destination group*/
	public void deleteGroup(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		if (destinationGroup.containsKey(s)) {
			destinationGroup.remove(s);
		}
	}
		
	/**
	 * Find the all services in particular place*/
	public String showDestinationRoute(String s) {
		return destination.get(s).toString();
	}
}
