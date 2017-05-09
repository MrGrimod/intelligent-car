package de.ye.car_track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.ye.car_dir.SERVO;
import de.ye.car_eng.ENGINE;

public class tr implements Runnable{

	@Override
	public void run() {
		try {
			ProcessBuilder pb = new ProcessBuilder("/home/pi/tr.sh");
			 Process p = pb.start();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			 String line = null;
				try {
			    	while ((line = reader.readLine()) != null) {
			    		if(line.equalsIgnoreCase("right")){
								SERVO.right();
					    } else if(line.equalsIgnoreCase("left")){
								SERVO.left();
					    } else if(line.equalsIgnoreCase("middle")){
								SERVO.middle();
					    } else if(line.equalsIgnoreCase("down_M")){
								ENGINE.FRONT();
								ENGINE.setSpeed(100);
					    } else if(line.equalsIgnoreCase("top_M")){
								ENGINE.FRONT();
								ENGINE.setSpeed(0);
					    } else if(line.equalsIgnoreCase("midlle_M")) {
							ENGINE.FRONT();
							ENGINE.setSpeed(0);
					    }
					 }
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
