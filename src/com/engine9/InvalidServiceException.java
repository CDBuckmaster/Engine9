package com.engine9;

public class InvalidServiceException extends Exception {
	public InvalidServiceException(){
        super();
    }
	
    public InvalidServiceException(String s){
        super(s);
    }

}
