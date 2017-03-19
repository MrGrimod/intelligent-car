package de.ye.car_eng;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class ENGINE {
	
	public static int SPEED;
	public static boolean DR=true;
	public static boolean FORWARD = true;
	
	static GpioController gpio = GpioFactory.getInstance();
	static GpioPinDigitalOutput pin_ENG_1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "ENG_DIR_1", PinState.HIGH);    
	static GpioPinDigitalOutput pin_ENG_2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "ENG_DIR_2", PinState.LOW);    
	

	
	public static void FRONT() throws InterruptedException{
		//System.out.println("FRONT");
            pin_ENG_1.high();
            pin_ENG_2.low();
        FORWARD=true;
	}
	
	public static void BACK() throws InterruptedException{
		//System.out.println("BACK");
            pin_ENG_2.high();
            pin_ENG_1.low();
        FORWARD=false;
	}
	public static void setSpeed(int speed) throws InterruptedException{
		//System.out.println("Speed:"+speed);
		if(DR==true){
			Gpio.wiringPiSetup();
			SoftPwm.softPwmCreate(24, 0, 200);
			SoftPwm.softPwmWrite(24, speed);
		} else if(FORWARD){
			Gpio.wiringPiSetup();
			SoftPwm.softPwmCreate(24, 0, 200);
			SoftPwm.softPwmWrite(24, 0);
		}
	}

	public static void STOP(){
		DR=false;
	}

	public static void START(){
		DR=true;
	}
}

