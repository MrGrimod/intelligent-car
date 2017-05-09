package de.ye.car_dir;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class SERVO {
	
	public static int PIN=23;
	public static boolean isInUse=false;
	
	public static void move(int angel) throws InterruptedException{
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		SoftPwm.softPwmWrite(PIN, angel);
	}
	public static void right() throws InterruptedException{
    	//System.out.println("MOVE_LEFT");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		
		if(!isInUse){
			SoftPwm.softPwmWrite(PIN, 10);		
		}
		
	}
	public static void left() throws InterruptedException{
		//System.out.println("MOVE_RIGHT");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		if(!isInUse){
			SoftPwm.softPwmWrite(PIN, 20);
		}
		
	}
	
	public static void middle() throws InterruptedException{
		//System.out.println("MOVE_MIDDLE");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		if(!isInUse){
			SoftPwm.softPwmWrite(PIN, 16);
		}
		
	}
	

	public static void middle_OVERRIDE() throws InterruptedException{
		isInUse=true;
		//System.out.println("MOVE_MIDDLE");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		SoftPwm.softPwmWrite(PIN, 15);
		//isInUse=false;
	}
	
	public static void right_OVERRIDE() throws InterruptedException{
		isInUse=true;
    	//System.out.println("MOVE_LEFT");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		SoftPwm.softPwmWrite(PIN, 10);
	}
	public static void left_OVERRIDE() throws InterruptedException{
		isInUse=true;
		//System.out.println("MOVE_RIGHT");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		SoftPwm.softPwmWrite(PIN, 20);
	}
	
	
	
}

