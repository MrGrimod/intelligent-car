package de.ye_car_gps;

import java.util.regex.Pattern;

public class GPS_METHODS {
	public static double GET_GPS_HANDY_LAT(){
		double result=0;
		Pattern p = Pattern.compile(",");
		String val = THREAD_GPS.s;
		String[] t=p.split(val);
		result= Double.parseDouble(t[0]);
		return result;
	}
	public static double GET_GPS_HANDY_LONG(){
		double result=0;
		Pattern p = Pattern.compile(",");
		String val = THREAD_GPS.s;
		String[] t=p.split(val);
		result= Double.parseDouble(t[1]);
		return result;
	}
}
