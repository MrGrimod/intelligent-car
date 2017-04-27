package de.luick.car_main;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import com.example.luick.boat_rc.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    //Raspberry s IP
    public static String RASP_IP = "192.168.42.1";

    //STATUS ob mit Raspberry verbunden
    public static boolean STATUS = false;

    //Manager für z.B. Gyro Sensor
    public static SensorManager sensorManager;


    public static boolean KIPP = false;
    public static Switch S_K;
    public static ImageButton B_RELOAD;
    public static String MODE;
    public static ImageButton uf;
    public static ImageButton f;
    public static ImageButton s;
    public static ImageButton ra;
    public static Switch is;
    public static Switch is_SIDE;
    public static Switch tr;
    public static ImageView wifi_warn;
    //TAG (Name vin Hautpklasse)
    private static final String TAG = "MainActivity";

    //Maub Methode
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisiere Elemente
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        B_RELOAD = (ImageButton) findViewById(R.id.B_RELOAD);
        uf = (ImageButton) findViewById(R.id.uf);
        f = (ImageButton) findViewById(R.id.f);
        s = (ImageButton) findViewById(R.id.s);
        ra = (ImageButton) findViewById(R.id.r);
        s.setImageResource(R.drawable.up);
        f.setImageResource(R.drawable.upg2);
        uf.setImageResource(R.drawable.upg3);
        is = (Switch) findViewById(R.id.S_IS);
        is_SIDE = (Switch) findViewById(R.id.S_IS_SIDE);
        tr = (Switch) findViewById(R.id.S_TR);
        wifi_warn = (ImageView) findViewById(R.id.wifi_warn);
        wifi_warn.setVisibility(View.INVISIBLE);

        //Starte Thread um die IP des Handy s zu übergeben
        Thread SEND_IP_TH = new Thread(new MainActivity.SEND_IP());
        SEND_IP_TH.start();

        //Listener für switch Button
        is.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    CONNECT.writer_sens_front.println("ON_FRONT");
                    CONNECT.writer_sens_front.flush();
                    Toast.makeText(getApplicationContext(), "Inelligent_ON", Toast.LENGTH_SHORT).show();
                } else {
                    CONNECT.writer_sens_front.println("OFF_FRONT");
                    CONNECT.writer_sens_front.flush();
                    Toast.makeText(getApplicationContext(), "Inelligent_OFF", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Listener für switch Button
        is_SIDE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    CONNECT.writer_sens_front.println("ON_SIDE");
                    CONNECT.writer_sens_front.flush();
                    Toast.makeText(getApplicationContext(), "Inelligent_ON_SIDE", Toast.LENGTH_SHORT).show();
                } else {
                    CONNECT.writer_sens_front.println("OFF_SIDE");
                    CONNECT.writer_sens_front.flush();
                    Toast.makeText(getApplicationContext(), "Inelligent_OFF_SIDE", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Listener für switch Button
        tr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    CONNECT.writer_track.println("ON");
                    CONNECT.writer_track.flush();
                    Toast.makeText(getApplicationContext(), "TRACKING....", Toast.LENGTH_SHORT).show();
                } else {
                    CONNECT.writer_track.println("OFF");
                    CONNECT.writer_track.flush();
                    Toast.makeText(getApplicationContext(), "TRACKING_OFF", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Listener für switch Button
        ra.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    KIPP = true;
                    ra.setImageResource(R.drawable.ar);

                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    KIPP = false;
                    ra.setImageResource(R.drawable.dr);
                }

                return true;
            }
        });
    }


    //Methode um WLAN qualität zu kontrollieren
    public void WIFI() {
        Context context = MainActivity.this;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int numberOfLevels = 5;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
        if (Math.round(level) < 2) {
            wifi_warn.setVisibility(View.VISIBLE);
        } else {
            wifi_warn.setVisibility(View.INVISIBLE);
        }
    }

    //Reconnect Button Listener
    public void B_RELOAD(View v) {
        Thread t = new Thread(new CONNECT());
        t.start();
    }

    //Modus: s =SLOW
    public void s(View v) {
        MODE = "s";
        s.setImageResource(R.drawable.up);
        f.setImageResource(R.drawable.upg2);
        uf.setImageResource(R.drawable.upg3);
        if (STATUS == true) {
            CONNECT.writer_modi.println("s");
            CONNECT.writer_modi.flush();
            Toast.makeText(getApplicationContext(), "SLOW", Toast.LENGTH_SHORT).show();
        }
    }

    //Modus: f=FAST
    public void f(View v) {
        MODE = "f";
        s.setImageResource(R.drawable.upg);
        f.setImageResource(R.drawable.up2);
        uf.setImageResource(R.drawable.upg3);
        if (STATUS == true) {
            CONNECT.writer_modi.println("f");
            CONNECT.writer_modi.flush();
            Toast.makeText(getApplicationContext(), "FAST", Toast.LENGTH_SHORT).show();
        }
    }

    //Modus: uf=ULTRA FAST
    public void uf(View v) {
        MODE = "uf";
        s.setImageResource(R.drawable.upg);
        f.setImageResource(R.drawable.upg2);
        uf.setImageResource(R.drawable.up3);
        if (STATUS == true) {
            CONNECT.writer_modi.println("uf");
            CONNECT.writer_modi.flush();
            Toast.makeText(getApplicationContext(), "ULTRA_FAST", Toast.LENGTH_SHORT).show();
        }
    }

    //Gyro Sensor changed Event
    @Override
    public void onSensorChanged(SensorEvent event) {

        //Control Wifi Qualität
        WIFI();


        if (KIPP == true) {
            float output = ((event.values[1] - 1) * (500 - 1)) / (20 - 1) + 1;
            //output= ((input-minInput) * (maxOutput-minOutput)) / (maxInput-minInput) + minOutput;
            float output_eng = ((event.values[2] - 1) * (500 - 1)) / (20 - 1) + 1;
            //output= ((input-minInput) * (maxOutput-minOutput)) / (maxInput-minInput) + minOutput;

            int send = Math.round(output);

            int send_eng = Math.round(output_eng);


            //Lenken
            if (send < 100 && send > -100) {
                CONNECT.writer_dir.println("middle");
                CONNECT.writer_dir.flush();
            } else {
                if (send > 100) {
                    CONNECT.writer_dir.println("right");
                    CONNECT.writer_dir.flush();
                } else {
                    if (send < -100) {
                        CONNECT.writer_dir.println("left");
                        CONNECT.writer_dir.flush();
                    }
                }
            }
            //Lenken


            if (STATUS == true) {
                if (send_eng < 75 && send_eng > -75) {
                    CONNECT.writer_eng.println("0");
                    CONNECT.writer_eng.flush();
                } else {
                    if (send_eng < -75) {
                        CONNECT.writer_eng.println("B");
                        CONNECT.writer_eng.println("0");
                        CONNECT.writer_eng.flush();
                    } else if (send_eng > 75) {
                        CONNECT.writer_eng.println("F");
                        CONNECT.writer_eng.println("199");
                        CONNECT.writer_eng.flush();

                    }
                }

                //Motor
                if (MODE == "s" || MODE == null) {

                    if (send_eng < 75 && send_eng > -75) {
                        CONNECT.writer_eng.println("0");
                        CONNECT.writer_eng.flush();
                    } else {
                        if (send_eng < -75) {
                            CONNECT.writer_eng.println("B");
                            //Speed slow
                            CONNECT.writer_eng.println("130");
                            CONNECT.writer_eng.flush();
                        } else if (send_eng > 75) {
                            CONNECT.writer_eng.println("F");
                            //Speed slow
                            CONNECT.writer_eng.println("130");
                            CONNECT.writer_eng.flush();

                        }
                    }
                } else if (MODE == "f") {

                    if (send_eng < 75 && send_eng > -75) {
                        CONNECT.writer_eng.println("0");
                        CONNECT.writer_eng.flush();
                    } else {
                        if (send_eng < -75) {
                            CONNECT.writer_eng.println("B");
                            //Speed fast
                            CONNECT.writer_eng.println("130");
                            CONNECT.writer_eng.flush();
                        } else if (send_eng > 75) {
                            CONNECT.writer_eng.println("F");
                            //Speed fast
                            CONNECT.writer_eng.println("170");
                            CONNECT.writer_eng.flush();

                        }
                    }

                } else if (MODE == "uf") {




                    if (send_eng < 75 && send_eng > -75) {
                        CONNECT.writer_eng.println("0");
                        CONNECT.writer_eng.flush();
                    } else {
                        if (send_eng < -75) {
                            CONNECT.writer_eng.println("B");
                            //Speed u fast
                            CONNECT.writer_eng.println("0");
                            CONNECT.writer_eng.flush();
                        } else if (send_eng > 75) {
                            CONNECT.writer_eng.println("F");
                            //Speed u fast
                            CONNECT.writer_eng.println("180");
                            CONNECT.writer_eng.flush();

                        }
                    }
                }

                //Motor
            }
        } else {
            if (STATUS == true) {
                CONNECT.writer_eng.println("0");
                CONNECT.writer_eng.flush();
                float output = ((event.values[1] - 1) * (500 - 1)) / (20 - 1) + 1;
                //output= ((input-minInput) * (maxOutput-minOutput)) / (maxInput-minInput) + minOutput;
                float output_eng = ((event.values[2] - 1) * (500 - 1)) / (20 - 1) + 1;
                //output= ((input-minInput) * (maxOutput-minOutput)) / (maxInput-minInput) + minOutput;

                int send = Math.round(output);
                //Lenken
                if (send < 100 && send > -100) {
                    CONNECT.writer_dir.println("middle");
                    CONNECT.writer_dir.flush();
                } else {
                    if (send > 100) {
                        CONNECT.writer_dir.println("right");
                        CONNECT.writer_dir.flush();
                    } else {
                        if (send < -100) {
                            CONNECT.writer_dir.println("left");
                            CONNECT.writer_dir.flush();
                        }
                    }
                }
                //Lenken

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //Methode um IP an Raspberry zu übermitteln
    public class SEND_IP implements  Runnable{
        @Override
        public void run(){
            while ((true)){
                if(STATUS==true){
                    WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
                    String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
                    CONNECT.writer_con.println(ip);
                    CONNECT.writer_con.flush();
                }
            }
        }
    }

}

