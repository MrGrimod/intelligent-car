package de.ye.car_led;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SERVER_LED implements Runnable {

    static ArrayList<PrintWriter> list_clientWriter;
	public static ServerSocket server;
	public static int PORT = 8686;
	public static ExecutorService executor;
	public static Socket client;
	 
	public SERVER_LED(int PORT){
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
			System.out.println("Server gestartet!_LED");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Server konnte nicht gestartet werden!_LED");
		}
		System.out.println("LISTEN_LED");
		while((true)){
			try {
				client = server.accept();
				if(client.isConnected()){
					System.out.println("Client Connected._LED");
					LED.LED_on_FINE();
				}
	            PrintWriter writer = new PrintWriter(client.getOutputStream());
	            list_clientWriter.add(writer);
				executor.execute(new THREAD_LED(client));
				System.out.println("STATUS CLIENT READY TO USE._LED");
			} catch (IOException e) {
				e.printStackTrace();
				LED.LED_on_ERR();
			}
		}
	}
}
