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
> -  For measuring distance the Intelligent Car uses [ ultrasonic sensor ](https://www.amazon.de/Ultrasonic-Distance-Measuring-Transducer-Arduino-5pcs/dp/B01CTZS4U6/ref=sr_1_2?ie=UTF8&qid=1491762506&sr=8-2&keywords=ultrasonic+sensor)
> -  To controll the high voltage of the motor the Intelligent Car uses a 10A, 5-25V  [ motor driver ](http://www.robotshop.com/eu/en/10a-5-30v-dual-channel-dc-motor-driver.html)
> - To controll the direction the Intelligent Car uses standart servos, you can get them everywhere.
> - To regulate the voltage i am using a standart 2A LM7805 with an heatsink.

Programming IDS?
-------------
For the app i am using [Android Studio](https://developer.android.com/studio/index.html).
For the programm on the Raspberry [Eclipse](https://www.eclipse.org/downloads/?) is the first choice.
