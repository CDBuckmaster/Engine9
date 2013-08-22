package com.engine9;

public class Listing{

	public long time;
	public String code;
	public String direction;
	
	public Listing(long _time, String _code, int _direction) {
		time = _time;
		code = _code;
		direction = directionToString(_direction);
	}
	
	private String directionToString(int dir){
		String[] directions = {"North", "South", "East", "West", "Inbound", "Outbound", "Inward", "Outward",
				"Upward", "Downward", "Clockwise", "Counterclockwise", "Direction1", "Direction2", ""};
		return directions[dir];
	}
	
}
