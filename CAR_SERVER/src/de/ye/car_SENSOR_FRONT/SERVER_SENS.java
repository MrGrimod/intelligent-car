package de.ye.car_SENSOR_FRONT;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.ye.car_led.LED;
import de.ye.car_led.THREAD_LED;

public class SERVER_SENS implements Runnable {

    static ArrayList<PrintWriter> list_clientWriter;
	public static ServerSocket server;
	public static int PORT = 9999;
	public static ExecutorService executor;
	 
	public SERVER_SENS(int PORT){
		this.PORT = PORT;
	
	}
	
	
	public static boolean isAlive(){
		Socket test;
		try {
			test = new Socket("",PORT);
			if(test.isConnected()){
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	public static void sendToAllClients(String message) {
        Iterator it = list_clientWriter.iterator();
       
        while(it.hasNext()) {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
        }
	}


	@Override
	public void run() {
		executor = Executors.newFixedThreadPool(30);
        list_clientWriter = new ArrayList<PrintWriter>();
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server gestartet!_SENS_FRONT");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Server konnte nicht gestartet werden!_SENS_FRONT");
			LED.LED_on_ERR();
		}
		System.out.println("LISTEN_SENS_FRONT");
		while((true)){
			try {
				Socket client = server.accept();
	            PrintWriter writer = new PrintWriter(client.getOutputStream());
	            list_clientWriter.add(writer);
				executor.execute(new THREAD_SENS(client));
				System.out.println("STATUS CLIENT READY TO USE._SENS_FRONT");
			} catch (IOException e) {
				e.printStackTrace();
				LED.LED_on_ERR();
			}
		}
		
	}
}
