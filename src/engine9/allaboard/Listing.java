package engine9.allaboard;

/**
 * It is a class to store the vehicle time data with its code and direction. 
 * */
public class Listing{

	public long time;
	public String code;
	public String direction;
	public int type;
	public String id;
	public String route;
	
	/**
	 * Set the vehicle information format
	 * 
	 * @param _time 
	 * 		the time for each vehicle in UTC 
	 * @param _code
	 * 		the vehicle code
	 * @param _direction
	 * 		the direction value to find the vehicle status
	 * */
	public Listing(long _time, String _code, int _direction, int _type, String _id, String _route) {
		
		time = _time;
		code = _code;
		direction = directionToString(_direction);
		type = _type;
		id = _id;
		route = _route;
	}
	
	/** 
	 * Get the vehicle direction
	 * 
	 * @param dir
	 * 		the valuable to get the direction
	 * */
	
	private String directionToString(int dir) {
		String[] directions = {"North", "South", "East", "West", "Inbound", "Outbound", "Inward", "Outward",
				"Upward", "Downward", "Clockwise", "Counterclockwise", "Direction1", "Direction2", ""};
		return directions[dir];
	}
	
}
