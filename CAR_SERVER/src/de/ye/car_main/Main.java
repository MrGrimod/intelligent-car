package de.ye.car_main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.ye.car_SENSOR_FRONT.SERVER_SENS;
import de.ye.car_con.SERVER_CON;
import de.ye.car_dir.SERVER_DIR;
import de.ye.car_eng.SERVER_ENG;
import de.ye.car_led.LED;
import de.ye.car_led.SERVER_LED;
import de.ye.car_modi.SERVER_MODI;
import de.ye.car_track.SERVER_TRACK;

public class Main {
	
	public static ExecutorService executor;
	 
	     public static void main(String args[]){
	    	 LED.ALL_LED_OFF();
	 		 executor = Executors.newFixedThreadPool(30);

	 		 executor.execute(new SERVER_CON(8989));
	 		 
	 		 executor.execute(new SERVER_ENG(8888));
	 		 
	 		 executor.execute(new SERVER_LED(8686));
	 		 
	 		 executor.execute(new SERVER_DIR(8787));
	 		 
	 		 executor.execute(new SERVER_SENS(9898));
	 		 
	 		 executor.execute(new SERVER_MODI(9999));
	 		 
	 		 executor.execute(new SERVER_TRACK(1212));
	 		 
	 		 
	 		 
	 		 
	 		 
	 		 Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			      public void run() {
			        LED.ALL_LED_OFF();
			      }
			}));
	     }
}