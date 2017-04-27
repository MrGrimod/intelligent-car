Intelligent Car
===================

The Intelligent Car is a Rc controlled Car,
which is capable of detecting faces or obstacles. 
It can be controlled by Mobile, it also offers different modes to drive with.

----------


How does it work?
-------------
The intelligent car communicates with Java TCP Sockets, both the app and the Raspberry applications are written in Java. The Raspberries program needs Pi4J to access the Raspberry Pi GPIOs.

> **C++/ Facedetection:**

> -  The Facedetection is written in C++, the project can be found [here](https://github.com/MrGrimod/intelligent-car-track).

Hardware/ Sensors, Motor Driver, Servo, Voltage regulator
-------------
> -  For measuring distance the Intelligent Car uses [ ultrasonic sensors ](https://www.amazon.de/Ultrasonic-Distance-Measuring-Transducer-Arduino-5pcs/dp/B01CTZS4U6/ref=sr_1_2?ie=UTF8&qid=1491762506&sr=8-2&keywords=ultrasonic+sensor)
> -  To controll the high voltage of the motor the Intelligent Car uses a 10A, 5-25V  [ motor driver ](http://www.robotshop.com/eu/en/10a-5-30v-dual-channel-dc-motor-driver.html)
> - To controll the direction the Intelligent Car uses standart servos, you can get them everywhere.
> - To regulate the voltage i am using a standart 2A LM7805 with an heatsink.


Required software 
-------------
To run CAR_SERVER.jar on the Raspberry you need to install [wiringPi](https://github.com/WiringPi/WiringPi). 
You also need to install Java.

Programming IDS?
-------------
> - For the app i am using [Android Studio](https://developer.android.com/studio/index.html).
> - For the programm on the Raspberry [Eclipse](https://www.eclipse.org/downloads/?) is the first choice.


Wifi access point 
-------------
For the wifi access point i m using the [ EDIMAX EW-7811UN ](https://www.amazon.de/EDIMAX-EW-7811UN-Wireless-Adapter-IEEE802-11b/dp/B003MTTJOY/ref=sr_1_1?ie=UTF8&qid=1492424488&sr=8-1&keywords=edimax+ew-7811un).
To setup the wifi access point two software components are required [HostAPD](https://www.daveconroy.com/turn-your-raspberry-pi-into-a-wifi-hotspot-with-edimax-nano-usb-ew-7811un-rtl8188cus-chipset/) to build up the wifi network and [dnsmasq](https://blog.marvin-menzerath.de/artikel/raspberry-pi-als-wlan-access-point-nutzen/) to bind the DHCP Server to the WLAN interface.

