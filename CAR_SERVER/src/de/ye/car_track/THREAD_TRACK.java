package de.ye.car_track;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import de.ye.car_eng.ENGINE;
import de.ye.car_led.LED;

public class THREAD_TRACK implements Runnable {
	
	public static Socket client;
	public static PrintWriter writer;
	public static OutputStream out;
	public static boolean tr_e;
	
	public THREAD_TRACK(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		Thread tr = new Thread(new tr());
		tr.start();
		try {
			//Streams
			out = client.getOutputStream();
			writer = new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			//-------------
			
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._TRACK");
			String s = null;
			tr_e=false;
			while((s = reader.readLine()) != null){
				if(s.equalsIgnoreCase("ON")){
					System.out.println("ON_TRACKING");
					tr_e=true;
				} else if(s.equalsIgnoreCase("OFF")){
					tr_e=false;
					System.out.println("OFF_TRACKING");
				}
			}
			System.out.println("STATUS CLIENT LISTENING READY._TRACK");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._TRACK");
		} catch (Exception e) {
		}
	}

}
