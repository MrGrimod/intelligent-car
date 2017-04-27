package de.ye.car_SENSOR_FRONT;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

import de.ye.car_eng.ENGINE;
import de.ye.car_modi.THREAD_MODI;

public class SENS_FRONT_MIDDLE_RIGHT implements Runnable{

    private final static float SOUND_SPEED = 340.29f;  
    
    private final static int TRIG_DURATION_IN_MICROS = 10;
    private final static int WAIT_DURATION_IN_MILLIS = 60; 

    private final static int TIMEOUT = 2100;
    private static int dist;
    
    private final static GpioController gpio = GpioFactory.getInstance();
    
    private GpioPinDigitalInput echoPin;
    private GpioPinDigitalOutput trigPin;
    public static int sens;
            
    private void prep_s( Pin echoPin, Pin trigPin ) {
        this.echoPin = gpio.provisionDigitalInputPin( echoPin );
        this.trigPin = gpio.provisionDigitalOutputPin( trigPin );
        this.trigPin.low();
    }
    
    public float measureDistance() throws TimeoutException {
        this.triggerSensor();
        this.waitForSignal();
        long duration = this.measureSignal();
        
        return duration * SOUND_SPEED / ( 2 * 10000 );
    }

    private void triggerSensor() {
        try {
            this.trigPin.high();
            Thread.sleep( 0, TRIG_DURATION_IN_MICROS * 1000 );
            this.trigPin.low();
        } catch (InterruptedException ex) {
        	//dist=5;
        }
    }
    
    private void waitForSignal() throws TimeoutException {
        int countdown = TIMEOUT;
        
        while( this.echoPin.isLow() && countdown > 0 ) {
            countdown--;
        }
        
        if( countdown <= 0 ) {
        	//dist=5;
        }
    }
    
    private long measureSignal() throws TimeoutException {
        int countdown = TIMEOUT;
        long start = System.nanoTime();
        while( this.echoPin.isHigh() && countdown > 0 ) {
            countdown--;
        }
        long end = System.nanoTime();
        
        if( countdown <= 0 ) {
        	//dist=5;
        }
        
        return (long)Math.ceil( ( end - start ) / 1000.0 );  
    }
    
    public void run() {
    	////////
        Pin echoPin = RaspiPin.GPIO_02; 
        Pin trigPin = RaspiPin.GPIO_03; 
    	////////
        prep_s( echoPin, trigPin );
        
        while( true ) {
            try {
            	Thread.sleep(CONFIG.LATENCY);
            	dist = Math.round(this.measureDistance());
            	//System.out.println("MIDDLE_RIGHT: "+dist);
                if(THREAD_SENS.I_S_FRONT==true){
                	if(THREAD_MODI.MODI.equalsIgnoreCase("s")){
                    	if(dist <=CONFIG.S_R_DIST){
                    		//System.out.println("MOTOR-OFF");
                        	ENGINE.STOP();
                    	} else {
                    		ENGINE.START();
                    	}
                	}
                	if(THREAD_MODI.MODI.equalsIgnoreCase("f")){
                    	if(dist <=CONFIG.F_R_DIST){
                    		//System.out.println("MOTOR-OFF");
                        	ENGINE.STOP();
                    	} else {
                    		ENGINE.START();
                    	}
                	}

                	if(THREAD_MODI.MODI.equalsIgnoreCase("uf")){
                    	if(dist <=CONFIG.UF_R_DIST){
                    		//System.out.println("MOTOR-OFF");
                        	ENGINE.STOP();
                    	} else {
                    		ENGINE.START();
                    	}
                	}
                }
            }
            catch( TimeoutException e ) {
                System.err.println( e );
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            try {
                Thread.sleep( WAIT_DURATION_IN_MILLIS );
            } catch (InterruptedException ex) {
                System.err.println( "Interrupt during trigger" );
            }
        }
    }
    private static class TimeoutException extends Exception {

        private final String reason;
        
        public TimeoutException( String reason ) {
            this.reason = reason;
        }
        
        @Override
        public String toString() {
            return this.reason;
        }
    }
}