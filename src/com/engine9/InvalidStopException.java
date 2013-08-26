package com.engine9;

public class InvalidStopException extends Exception {
	public InvalidStopException(){
        super();
    }
	
    public InvalidStopException(String s){
        super(s);
    }
}
