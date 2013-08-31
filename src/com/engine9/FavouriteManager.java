package com.engine9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import android.content.Context;

/*Control the file input output for the favourite*/

public class FavouriteManager {

	/**
	 * Add the favourite service in to output file
	 * 
	 * @param fav
	 * 		favourite service code
	 * @param context
	 * 		the 
	 * 
	 * @throws IOException
	 * 		No output
	 * */
	public static void AddFavourite(String fav, Context context){
		FileOutputStream outputStream;
		try {
			outputStream =context.openFileOutput("favourites.txt", Context.MODE_APPEND);
			outputStream.write((fav + '\n').getBytes());
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
	 * 		the 
	 * 
	 * @throws
	 * 
	 * */
	public static void deleteFavourite(String fav, Context context){
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput("favourites.txt")));
			String inputStr;
			StringBuffer stringBuffer = new StringBuffer();
			while((inputStr = input.readLine()) !=  null){
				if(inputStr != fav){
					stringBuffer.append(inputStr + '\n');
				}
			}
			input.close();
			
			FileOutputStream outputStream;
			outputStream =context.openFileOutput("favourites.txt", Context.MODE_PRIVATE);
			outputStream.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the favourite service from the file and construct them into list 
	 * */
	public static Vector<String> getFavourites(Context context){
		
		try{
			Vector<String> retVector = new Vector<String>();
			BufferedReader input = new BufferedReader(new InputStreamReader(
					context.openFileInput("favourites.txt")));
			String inputStr;
			while((inputStr = input.readLine()) !=  null){
				retVector.add(inputStr);
			}
			
			return retVector;
		} catch(Exception e){
			return null;
		}
	}
	
	
}
