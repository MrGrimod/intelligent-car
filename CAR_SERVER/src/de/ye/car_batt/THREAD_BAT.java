package de.ye.car_batt;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class THREAD_BAT implements Runnable {
	
	public static Socket client;
	
	public THREAD_BAT(Socket client) {
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
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._BAT");
			String s = null;

			while((s = reader.readLine()) != null){
				System.out.println(s);
			}
			System.out.println("STATUS CLIENT LISTENING READY._BAT");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._BAT");
		} catch (Exception e) {
			
		}
	}

}
