package com.engine9;

/**
 * An exception indicating an invalid primeter
 * */

@SuppressWarnings("serial")
public class InvalidPointerException extends Exception {
	 public InvalidPointerException(){
	        super();
	    }
		
	    public InvalidPointerException(String s){
	        super(s);
	    }
}
