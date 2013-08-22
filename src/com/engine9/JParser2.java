package com.engine9;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


public class JParser2 {

    public static JSONObject main(String arg) throws Exception {
     
    	
        try{
        JSONObject json = (JSONObject) JSONSerializer.toJSON( arg ); 
        return json;
        } catch(Exception e){
        	return null;
        }
        
    }
}
