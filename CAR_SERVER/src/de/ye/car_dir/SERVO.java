package de.ye.car_dir;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class SERVO {
	
	public static int PIN=23;
	
	public static void move(int angel) throws InterruptedException{

//		  GpioController gpio = GpioFactory.getInstance();
//	      Pin pin = RaspiPin.GPIO_23;
//
//	        GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(pin);
//	        
//	        com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
//	        com.pi4j.wiringpi.Gpio.pwmSetRange(1000);
//	        com.pi4j.wiringpi.Gpio.pwmSetClock(500);
//	        
//	        pwm.setPwm(angel);
//	        
    	//System.out.println("MOVE_LEFT");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		
		//1 = Links
		//49 = Rechts
		SoftPwm.softPwmWrite(PIN, angel);
    		
	}
	public static void right() throws InterruptedException{
    	//System.out.println("MOVE_LEFT");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		
		//1 = Links
		//49 = Rechts
		SoftPwm.softPwmWrite(PIN, 10);
		
	}
	public static void left() throws InterruptedException{
		//System.out.println("MOVE_RIGHT");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		
		//1 = Links 
		//49 = Rechts
		SoftPwm.softPwmWrite(PIN, 20);
	}
	public static void middle() throws InterruptedException{
		//System.out.println("MOVE_MIDDLE");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(PIN, 0, 50);
		
		//1 = Links
		//49 = Rechts
		SoftPwm.softPwmWrite(PIN, 15);
	}
}

