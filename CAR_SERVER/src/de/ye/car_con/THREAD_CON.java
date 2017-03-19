package de.ye.car_con;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import de.ye.car_eng.ENGINE;
import de.ye.car_eng.THREAD_ENG;
import de.ye.car_led.LED;

public class THREAD_CON implements Runnable {
	
	public static Socket client;
	public static boolean ALIVE=false;
	public static boolean t=true;
	public static int IP;
	public static boolean end = false;
	public static String TRACK_IP;
	
	public THREAD_CON(Socket client) {
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
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._CON");
			String s = null;

			while(t=true){
				TRACK_IP=reader.readLine();
					try{
				    	if(InetAddress.getByName(TRACK_IP).isReachable(80)){
							//System.out.println("OK");
				    		//"192.168.42.15"
							LED.LED_on_FINE();
				    	} else {
    							if(InetAddress.getByName(TRACK_IP).isReachable(80)){
    								LED.LED_on_FINE();
    					    	} else {
    					    		if(InetAddress.getByName(TRACK_IP).isReachable(80)){
        								LED.LED_on_FINE();
        					    	} else {
        					    		if(InetAddress.getByName(TRACK_IP).isReachable(80)){
            								LED.LED_on_FINE();
            					    	} else {
            					    		if(InetAddress.getByName(TRACK_IP).isReachable(80)){
                								LED.LED_on_FINE();
                					    	} else {
                					    		if(InetAddress.getByName(TRACK_IP).isReachable(80)){
                    								LED.LED_on_FINE();
                    					    	} else {
                    					    		if(InetAddress.getByName(TRACK_IP).isReachable(80)){
                        								LED.LED_on_FINE();
                        					    	} else {
                        					    		if(InetAddress.getByName(TRACK_IP).isReachable(80)){
                        								LED.LED_on_FINE();
	                        					    	} else {
	                        					    			System.out.println("STOP >> REASON NO_CONNECTION");
	                        		                    		ENGINE.STOP();
	                        	    							LED.LED_off_FINE();
	                        					    	}
                        					    	}
                    					    	}
                					    	}
            					    	}
        					    	}
    					    	}
				    	}
					} catch (Exception e){
				}
			}
			
			System.out.println("STATUS CLIENT LISTENING READY._CON");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._CON");
		} catch (Exception e) {
			
		}
	}


}
