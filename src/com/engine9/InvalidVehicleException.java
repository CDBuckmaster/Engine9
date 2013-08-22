package com.engine9;

public class InvalidVehicleException extends Exception {
	public InvalidVehicleException(){
        super();
    }
	
    public InvalidVehicleException(String s){
        super(s);
    }
}
