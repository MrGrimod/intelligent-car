package de.ye.car_modi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SERVER_MODI implements Runnable {

    static ArrayList<PrintWriter> list_clientWriter;
	public static ServerSocket server;
	public static int PORT = 8686;
	public static ExecutorService executor;
	public static Socket client;
	 
	public SERVER_MODI(int PORT){
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
			System.out.println("Server gestartet!_MODI");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Server konnte nicht gestartet werden!_MODI");
		}
		System.out.println("LISTEN_MODI");
		while((true)){
			try {
				client = server.accept();
				if(client.isConnected()){
					System.out.println("Client Connected._MODI");
				}
	            PrintWriter writer = new PrintWriter(client.getOutputStream());
	            list_clientWriter.add(writer);
				executor.execute(new THREAD_MODI(client));
				System.out.println("STATUS CLIENT READY TO USE._MODI");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
