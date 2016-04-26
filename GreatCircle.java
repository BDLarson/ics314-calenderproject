package ics314;

import java.util.*;


public class GreatCircle {
	/*
	 * Calculate the Great Circle distance
	 * Given latitude and longitude of two locations
	 * 
	 * latitude and longitude given in degrees 
	 * convert to radians for java.Math to calculate
	 * 
	 * lat1, long1, lat2, long2
	 * 
	 * central angle =
	 *  arccos(sinlat1 * sinlat2 + coslat1 * coslat2 * cos(long1 - long2))
	 * 
	 * distance = radius * central angle
	 * @author Steven Roylance
	 * @date 4/7/2016
	 */
	private double lat1;
	private double lat2;
	private double lon1;
	private double lon2;
	//central angle
	private double centangle;
	//returns info back in km, can convert later
	private static double earthkm = 6371.0;
	private static double earthmi = 3958.75;
	//distance in km
	private double distance = 0;

	public void GreatCircle(){
		
	}
	
	//insert in degrees convert to radians for calculation
	public GreatCircle(String lat1,String lon1, String lat2, String lon2){
		this.lat1 = Math.toRadians(Double.parseDouble(lat1));
		this.lon1 = Math.toRadians(Double.parseDouble(lon1));
		this.lat2 = Math.toRadians(Double.parseDouble(lat2));
		this.lon2 = Math.toRadians(Double.parseDouble(lon2));
	}

	public void calcDistance(){
		centangle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                 + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
		//convert back to degrees
		//this.centangle = Math.toDegrees(centangle);
		this.distance = earthkm * centangle;
	}
	
	public double getDistance(){
		calcDistance();
		if(distance != 0){
			return distance;
		}
		else{
			System.out.println("Error distance not calculated. Use calcDistance()");
			return 0;
		}
	}
	
}

