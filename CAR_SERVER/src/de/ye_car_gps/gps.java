package de.ye_car_gps;

import se.hirt.pi.adafruit.gps.GPS;
import se.hirt.pi.adafruit.gps.GPSListener;
import se.hirt.pi.adafruit.gps.PositionEvent;
import se.hirt.pi.adafruit.gps.VelocityEvent;

public class gps implements Runnable{
	
	public static String val;
   
    
    public void run() {
    	GPS gps = new GPS();
    	gps.addListener(new GPSListener() {
    	    @Override
    	    public void onEvent(PositionEvent event) {
    	        //System.out.println(event);
    	        val=event.getLocation().toString();
    	        System.out.println(val);
    	        THREAD_GPS.writer.write(event.toString());
    	        THREAD_GPS.writer.flush();
    	    }

    	    @Override
    	    public void onEvent(VelocityEvent event) {
    	        System.out.println(event);
    	    }
    	});
    	while(true){}
    }
}