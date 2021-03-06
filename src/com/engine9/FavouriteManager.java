package com.engine9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import android.content.Context;
import android.util.Log;

/**
 * Class for managing the input/output of the favourites file
 * @author callumbuckmaster
 *
 */

public class FavouriteManager {

	/**
	 * Add the favourite service into the favourites file
	 * 
	 * @param fav
	 * 		the code of the service
	 * @param route 
	 * 		the route of the service
	 * @param context
	 * 		the application context
	 * 
	 * @throws IOException
	 * 		No output
	 * */
	public static void AddFavourite(String fav, String route, Context context){
		FileOutputStream outputStream;
		try {
			outputStream =context.openFileOutput("favourites.txt", Context.MODE_APPEND);
			outputStream.write(("Name:" + fav + "|" + "Route:" + route + "|Colour: |Description: " + '\n').getBytes());
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Delete the favourite service from the group
	 * 
	 * @param fav
	 * 		favourite service code in exist file
	 * @param context
	 * 		the application context
	 * 
	 * 
	 * */
	public static void deleteFavourite(String fav, Context context){
		new File(context.getFilesDir(), "favourites.txt");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput("favourites.txt")));
			String inputStr;
			String stringBuffer = "";
			while((inputStr = input.readLine()) !=  null){
				String[] parts = inputStr.split("\\|");
				Log.d("DEBUG", parts[0]);
				if(!parts[0].split(":")[1].equals(fav)){
					stringBuffer += (inputStr + '\n');
				}
			}
			input.close();
			
			FileOutputStream outputStream;
			outputStream =context.openFileOutput("favourites.txt", Context.MODE_PRIVATE);
			outputStream.write(stringBuffer.getBytes());
			outputStream.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes all favourites
	 * @param context The application context
	 */
	public static void deleteAllFavourites(Context context){
		
		try {
			FileOutputStream outputStream;
			outputStream =context.openFileOutput("favourites.txt", Context.MODE_PRIVATE);
			outputStream.write("".getBytes());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the all favourite services from the file and construct them into list 
	 * @param context The application context
	 * @return A vector of FavouriteInfo objects
	 * */
	public static Vector<FavouriteInfo> getFavourites(Context context){
		try{
			Vector<FavouriteInfo> retVector = new Vector<FavouriteInfo>();
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput("favourites.txt")));
			String inputStr;
			while((inputStr = input.readLine()) !=  null){
				//retVector.add(inputStr);
				Log.d("DEBUG", inputStr);
				String[] parts = inputStr.split("\\|");
				FavouriteInfo fi = new FavouriteInfo();
				for(String s : parts){
					String[] vals = s.split(":");
					if(vals[0].equals("Name")){
						fi.name = vals[1];
					}
					if(vals[0].equals("Route")){
						fi.route = vals[1];
					}
					if(vals[0].equals("Colour")){
						fi.colour = vals[1];
					}
					if(vals[0].equals("Description")){
						fi.description = vals[1];
					}
				}
				retVector.add(fi);
			}
			
			return retVector;
		} catch(Exception e){
			e.printStackTrace();
			return new Vector<FavouriteInfo>();
		}
	}
	
	/**
	 * Checks to see if a service is in favourites
	 * @param context The application context
	 * @param fav The code of the service
	 * @return True if in favourites, false otherwise
	 */
	public static Boolean inFavourites(Context context, String fav){
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput("favourites.txt")));
			String inputStr;
			while((inputStr = input.readLine()) !=  null){
				String[] parts = inputStr.split("\\|");
				parts = parts[0].split(":");
				if(parts[1].equals(fav)){
					return true;
				}
			}
			
			return false;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Sets the colour value of the favourite service
	 * @param context The application context
	 * @param fav The code of the service
	 * @param colour The name of the colour (not the hex code)
	 */
	public static void setColour(Context context, String fav, String colour){
		new File(context.getFilesDir(), "favourites.txt");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput("favourites.txt")));
			String inputStr;
			String stringBuffer = "";
			while((inputStr = input.readLine()) !=  null){
				String[] parts = inputStr.split("\\|");
				if(!parts[0].split(":")[1].equals(fav)){
					stringBuffer += (inputStr + '\n');
				}
				else{
					//Boolean colourSet = false;
					int count = 0;
					for(String s : parts){
						if(s.split(":")[0].equals("Colour")){
							//colourSet = true;
							stringBuffer += "Colour:" + colour;
							if(count < parts.length - 1){
								stringBuffer += "|";
							}
						}
						else{
							stringBuffer += s;
							if(count < parts.length - 1){
								stringBuffer += "|";
							}
						}
					}
					/*
					if(!colourSet){
						stringBuffer += "Colour:" + colour;
					}*/
					stringBuffer += '\n';
				}
			}
			input.close();
			
			FileOutputStream outputStream;
			outputStream =context.openFileOutput("favourites.txt", Context.MODE_PRIVATE);
			outputStream.write(stringBuffer.getBytes());
			outputStream.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the colour of a favourite service
	 * @param context The application context
	 * @param fav The code of the service
	 * @return The name of the colour (not the hex code)
	 */
	public static String getColour(Context context, String fav){
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput("favourites.txt")));
			String inputStr;
			while((inputStr = input.readLine()) !=  null){
				String[] parts = inputStr.split("\\|");
				String[] p = parts[0].split(":");
				if(p[1].equals(fav)){
					parts = parts[2].split(":");
					return parts[1];
				}
			}
			
			return " ";
		} catch(Exception e){
			e.printStackTrace();
			return " ";
		}
	}
	
	/**
	 * Sets the description for a favourite service
	 * @param context The application context
	 * @param fav The code of the service
	 * @param description A user written description about the service
	 */
	public static void setDescription(Context context, String fav, String description){
		new File(context.getFilesDir(), "favourites.txt");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput("favourites.txt")));
			String inputStr;
			String stringBuffer = "";
			while((inputStr = input.readLine()) !=  null){
				String[] parts = inputStr.split("\\|");
				if(!parts[0].split(":")[1].equals(fav)){
					stringBuffer += (inputStr + '\n');
				}
				else{
					//Boolean descriptionSet = false;
					int count = 0;
					for(String s : parts){
						if(s.split(":")[0].equals("Description")){
							//descriptionSet = true;
							stringBuffer += "Description:" + description;
							if(count < parts.length - 1){
								stringBuffer += "|";
							}
							
						}
						else{
							stringBuffer += s;
							if(count < parts.length - 1){
								stringBuffer += "|";
							}
						}
						count ++;
					}
					/*if(!descriptionSet){
						stringBuffer += "Description:" + description;
					}*/
					stringBuffer += '\n';
				}
			}
			input.close();
			
			FileOutputStream outputStream;
			outputStream =context.openFileOutput("favourites.txt", Context.MODE_PRIVATE);
			outputStream.write(stringBuffer.getBytes());
			outputStream.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
