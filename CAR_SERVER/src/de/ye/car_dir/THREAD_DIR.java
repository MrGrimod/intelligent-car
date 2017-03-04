package de.ye.car_dir;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import de.ye.car_eng.ENGINE;
import de.ye.car_led.LED;
import de.ye.car_track.THREAD_TRACK;

public class THREAD_DIR implements Runnable {
	
	public static Socket client;
	
	public THREAD_DIR(Socket client) {
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
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._DIR");
			String s = null;
			
			while((s = reader.readLine()) != null){
					if(THREAD_TRACK.tr_e==false){
					//System.out.println(s);
					if(s.matches("\\d*")){
					      //Zahl
						SERVO.move(Integer.parseInt(s));
					} else {
						if(s.equalsIgnoreCase("left")){
							SERVO.left();
							//System.out.println("left");
						} else {
							if(s.equalsIgnoreCase("right")){
								SERVO.right();
								//System.out.println("right");
							} else {
								if(s.equalsIgnoreCase("middle")){
									SERVO.middle();
									//System.out.println("middle");
								}
							}
						}
					}
				}	
			}
			System.out.println("STATUS CLIENT LISTENING READY._DIR");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._DIR");
		} catch (Exception e) {
		}
	}

}
