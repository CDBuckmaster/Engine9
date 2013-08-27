package com.engine9;

import java.io.FileReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/*
 * Class for parsing a Json string into a JsonObject
 */
public class JParser2 {

    public static JsonObject main(String arg) throws Exception {
     
    	//Uses GSON classes to parse the string, has a possibility to cause
    	//an exception
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
