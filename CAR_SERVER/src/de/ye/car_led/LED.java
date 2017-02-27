package de.ye.car_led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LED {
	

    static GpioController gpio = GpioFactory.getInstance();
    
    static GpioPinDigitalOutput pin_WARN = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "LED_WARN", PinState.LOW);
    
	public static void LED_on_WARN(){
	    pin_WARN.high();
	}
	public static void LED_off_WARN(){
	    pin_WARN.low();
	}
		static GpioPinDigitalOutput pin_ERR = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "LED_ERR", PinState.LOW);
	
	public static void LED_on_ERR(){
	    pin_ERR.high();
	}
	public static void LED_off_ERR(){
	    pin_ERR.low();
	}
	static GpioPinDigitalOutput pin_FINE = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "LED_FINE", PinState.LOW);
    
	public static void LED_on_FINE(){
		pin_FINE.high();
	}
	public static void LED_off_FINE(){
		pin_FINE.low();
	}
	public static void ALL_LED_OFF(){
		LED_off_FINE();
		LED_off_ERR();
		LED_off_WARN();
	}
}
