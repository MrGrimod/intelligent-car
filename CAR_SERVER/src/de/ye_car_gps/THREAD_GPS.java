package de.ye_car_gps;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import de.ye.car_eng.ENGINE;
import de.ye.car_led.LED;

public class THREAD_GPS implements Runnable {
	
	public static Socket client;
	public static PrintWriter writer;
	public static OutputStream out;
	public static boolean I_GPS=false;
	public static String s;
	
	public THREAD_GPS(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			//Streams
			out = client.getOutputStream();
			writer = new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			//-------------
	 		Thread gps_car = new Thread(new gps());
			gps_car.start();
			
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._GPS");
			s = null;
			while((s = reader.readLine()) != null){
				//System.out.println("GPS LAT:" + GPS_METHODS.GET_GPS_HANDY_LAT());
				//System.out.println("GPS LONG:" + GPS_METHODS.GET_GPS_HANDY_LONG());
				if(s.equalsIgnoreCase("ON")){
					I_GPS=true;
				} else if(s.equalsIgnoreCase("OFF")){
					I_GPS=false;
            		//ENGINE.START();
				}
			}
			System.out.println("STATUS CLIENT LISTENING READY._GPS");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._GPS");
		} catch (Exception e) {
		}
	}

}
