package com.engine9;

import java.io.FileReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class JParser2 {

    public static JsonObject main(String arg) throws Exception {
     
    	
        try{
        	JsonParser parser = new JsonParser();
        	JsonElement jsonElement = parser.parse(arg);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            return jsonObject;
        } catch(Exception e){
        	return null;
        }
        
    }
}
