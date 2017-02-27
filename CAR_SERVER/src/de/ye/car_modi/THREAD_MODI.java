package de.ye.car_modi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class THREAD_MODI implements Runnable {
	
	public static Socket client;
	public static String MODI="s";
	
	public THREAD_MODI(Socket client) {
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
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._MODI");
			String s = null;

			while((s = reader.readLine()) != null){
				System.out.println(s);
				if(s.equalsIgnoreCase("s")){
					MODI="s";
					System.out.println("SLOW");
				} else if(s.equalsIgnoreCase("f")){
					MODI="f";
					System.out.println("FAST");
				} else if(s.equalsIgnoreCase("uf")){
					MODI="uf";
					System.out.println("ULTRA_FAST");
				}
			}
			System.out.println("STATUS CLIENT LISTENING READY._MODI");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._MODI");
		} catch (Exception e) {
			
		}
	}

}
