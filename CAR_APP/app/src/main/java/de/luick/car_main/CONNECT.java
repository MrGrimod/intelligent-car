package de.luick.car_main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CONNECT implements Runnable {


    public static PrintWriter writer_led;
    public static BufferedReader reader_led;

    public static PrintWriter writer_dir;
    public static BufferedReader reader_dir;

    public static PrintWriter writer_eng;
    public static BufferedReader reader_eng;

    public static PrintWriter writer_sens_front;
    public static BufferedReader reader_sens_front;

    public static PrintWriter writer_modi;
    public static BufferedReader reader_modi;

    public static PrintWriter writer_con;
    public static BufferedReader reader_con;

    public static PrintWriter writer_track;
    public static BufferedReader reader_track;

    public static PrintWriter writer_gps;
    public static BufferedReader reader_gps;

    //Clients
    public static Socket client_led;
    public static  Socket client_eng;
    public static Socket client_dir;
    public static Socket client_sens;
    public static Socket client_modi;
    public static Socket client_con;
    public static Socket client_track;
    public static Socket client_gps;


    //TextView t = (TextView)  findViewById(R.id.TV_NC);

    @Override
    public void run() {
        try {
            System.out.println("Connecting");


            client_dir = new Socket();
            client_led = new Socket();
            client_eng = new Socket();
            client_sens = new Socket();
            client_modi = new Socket();
            client_con = new Socket();
            client_track = new Socket();
            client_gps = new Socket();

                client_led.connect(new InetSocketAddress(MainActivity.RASP_IP, 8686), 100);
                client_dir.connect(new InetSocketAddress(MainActivity.RASP_IP, 8787), 100);
                client_eng.connect(new InetSocketAddress(MainActivity.RASP_IP, 8888), 100);
                client_con.connect(new InetSocketAddress(MainActivity.RASP_IP, 8989), 100);
                client_sens.connect(new InetSocketAddress(MainActivity.RASP_IP, 9898), 100);
                client_modi.connect(new InetSocketAddress(MainActivity.RASP_IP, 9999), 100);
                client_track.connect(new InetSocketAddress(MainActivity.RASP_IP, 1212), 100);
                client_gps.connect(new InetSocketAddress(MainActivity.RASP_IP, 1313), 100);




                //Streams LED
                OutputStream out_LED = client_led.getOutputStream();
                writer_led = new PrintWriter(out_LED);

                InputStream in_LED = client_led.getInputStream();
                reader_led = new BufferedReader(new InputStreamReader(in_LED));
                //-------------

                //Streams DIR
                OutputStream out_DIR = client_dir.getOutputStream();
                writer_dir = new PrintWriter(out_DIR);

                InputStream in_DIR = client_dir.getInputStream();
                reader_dir = new BufferedReader(new InputStreamReader(in_DIR));
                //-------------

                //Streams ENG
                OutputStream out_ENG = client_eng.getOutputStream();
                writer_eng = new PrintWriter(out_ENG);

                InputStream in_ENG = client_eng.getInputStream();
                reader_eng = new BufferedReader(new InputStreamReader(in_ENG));
                //-------------

                //Streams SENS_FRONT
                OutputStream out_sens_front = client_sens.getOutputStream();
                writer_sens_front = new PrintWriter(out_sens_front);

                InputStream in_sens_front = client_sens.getInputStream();
                reader_sens_front = new BufferedReader(new InputStreamReader(in_sens_front));
                //-------------

                //Streams MODI
                OutputStream out_MODI = client_modi.getOutputStream();
                writer_modi = new PrintWriter(out_MODI);

                InputStream in_MODI = client_modi.getInputStream();
                reader_modi = new BufferedReader(new InputStreamReader(in_MODI));
                //-------------

                //Streams CON
                OutputStream out_CON = client_con.getOutputStream();
                writer_con = new PrintWriter(out_CON);

                InputStream in_CON = client_con.getInputStream();
                reader_con = new BufferedReader(new InputStreamReader(in_CON));
                //-------------

                //Streams CTRACK
                OutputStream out_TRACK = client_track.getOutputStream();
                writer_track = new PrintWriter(out_TRACK);

                InputStream in_TRACK = client_track.getInputStream();
                reader_track = new BufferedReader(new InputStreamReader(in_TRACK));
                //-------------

                //Streams GPS
                OutputStream out_GPS = client_gps.getOutputStream();
                writer_gps = new PrintWriter(out_GPS);

                InputStream in_GPS = client_gps.getInputStream();
                reader_gps = new BufferedReader(new InputStreamReader(in_GPS));
                //-------------

            if (client_led.isConnected()) {
                System.out.println("Connected");
                MainActivity.STATUS = true;
            }
        }catch(IOException e){
                System.out.println("Konnte nicht verbinden");
                MainActivity.STATUS = false;
            }
        }
    }