package com.engine9;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;


public class Request extends AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		try{
			InputStream is = null;
			
			try{
				
				URL url = new URL(arg0[0]);
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(10000);
		        conn.setConnectTimeout(15000);
		        conn.setRequestMethod("GET");
		        conn.setDoInput(true);
		        
		        conn.connect();
		        int response = conn.getResponseCode();
	
		        is = conn.getInputStream();
		        
		        //FileInputStream fis = new FileInputStream("c:/sample.txt");
		        
		        String contentAsString = new Scanner(is,"UTF-8").useDelimiter("\\A").next();
		        is.close();
		       // Log.e("DEBUG", "The webpage farted out: " + contentAsString);
		        return contentAsString;
			}
			 finally {
		        if (is != null) {
		            is.close();
		        } 
			 }
		} catch (Exception e){
			Log.e("DEBUG", e.toString());
			return null;
		}
	}
	
	@Override
	 protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}

}
