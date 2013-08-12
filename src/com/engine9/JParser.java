package com.engine9;


import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

import android.util.Log;

public class JParser {
	public static Vector<Map> main(String in)
	{
		JSONParser jParser = new JSONParser();
		Vector<Map> retVec = new Vector<Map>();
		
		try{
			for (Object o : (JSONArray) jParser.parse(in))
			{
				retVec.add((Map) o);
			}
			return retVec;
		} catch (ParseException e){
			Log.e("Parse", e.toString());
			return null;
		}
	}
	
	public static JSONObject pObject(String in)
	{
		JSONParser jParser = new JSONParser();
		
		try{
			return (JSONObject) jParser.parse(in);
			
		} catch (ParseException e){
			Log.e("Parse", e.toString());
			return null;
		}
	}
}
