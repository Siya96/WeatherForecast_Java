package lab2;

import java.util.HashMap;

/**
 * 
 * @author Siyabend Revend 
 *
 */

/*
 * This class creates a city object which stores different value for a city.
 */
public class City {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	

	private String cityName;
	private  String latitude;
	private String longitude;
	private String cityTemp;
	
	
	
	
	/**
	 * Whenever a city object is created, it is given a name, a latitude and longitude that 
	 * are extracted from the Xml-document (places.xml).
	 * @param cityName
	 * @param latitude
	 * @param longitude
	 */
	public City(String cityName, String latitude, String longitude) {
		
		this.cityName = cityName;
		this.latitude = latitude;
		this.longitude =longitude;
		cityTemp = "";
		
	}
	
	/**
	 * 
	 * @return the city-name.
	 */
	public String getCityName() {
		
		return cityName;
	}
	
	/**
	 * 
	 * @return the latitude
	 */
	public String getLatitude() {
		
		return latitude;
	}
	
	/**
	 * 
	 * @return the longitude.
	 */
	public String getLongitude() {
		
		return longitude;
	}
	
	/**
	 * 
	 * @return the city temperature
	 */
	public String getCityTemp() {
		
		return cityTemp;
	}

	
	/**
	 * Sets the city temperature.
	 * @param temp
	 */
	public void setCityTemp(String temp) {
		
		cityTemp = temp;
		
		
	}
	


}

	
