package de.ye.car_eng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.pi4j.wiringpi.SoftPwm;

import de.ye.car_led.LED;
import de.ye.car_track.THREAD_TRACK;

public class THREAD_ENG implements Runnable {
	
	public static Socket client;
	
	public THREAD_ENG(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {

			//Streams
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			//-------------
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._ENG");
			String s = null;
			
			while((s = reader.readLine()) != null){
				if(THREAD_TRACK.tr_e==false){
					//System.out.println(s);
					if(s.matches("\\d*")){
					      //Zahl
						ENGINE.setSpeed(Integer.parseInt(s));
						//System.out.println(s);
					} else {
						if(s.equalsIgnoreCase("B")){
							ENGINE.BACK();
						} else {
							if(s.equalsIgnoreCase("F")){
								ENGINE.FRONT();
							}
						}
					}
				}
			}
			System.out.println("STATUS CLIENT LISTENING READY._ENG");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._ENG");
		} catch (Exception e) {
		}
	}
}
