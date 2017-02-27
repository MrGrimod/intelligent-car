package de.ye.car_SENSOR_FRONT;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import de.ye.car_eng.ENGINE;
import de.ye.car_led.LED;

public class THREAD_SENS implements Runnable {
	
	public static Socket client;
	public static PrintWriter writer;
	public static OutputStream out;
	public static boolean I_S=false;
	
	public THREAD_SENS(Socket client) {
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
	 		 Thread tttt = new Thread(new Sens());
	 		 tttt.start();
			
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._SENS_FRONT");
			String s = null;
			while((s = reader.readLine()) != null){
				if(s.equalsIgnoreCase("ON")){
					I_S=true;
				} else if(s.equalsIgnoreCase("OFF")){
					I_S=false;
            		ENGINE.START();
				}
			}
			System.out.println("STATUS CLIENT LISTENING READY._SENS_FRONT");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._SENS_FRONT");
		} catch (Exception e) {
		}
	}

}
