package com.example.vivekanand.timer;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    int t=0;
    int c;
    int enablecheck = 0;
    int stopslideshow = 0;
    int stoptimer = 0;
    int i[] = {R.drawable.naruto,R.drawable.ace,R.drawable.itachi,R.drawable.itachi2,R.drawable.luffy};
    int k;
    ImageView viveksimage;
    Spinner spinner;
    Button enablebutton,disablebutton,viveksbutton;
    SensorManager mSensorManager;
    Sensor mSensor;
    TextView vivekstext;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            t++;

            vivekstext.setText(Integer.toString(t));

        }

    };
    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(c%5)
            {
                case 0:
                    viveksimage.setImageResource(R.drawable.ace);
                    break;
                case 1:
                    viveksimage.setImageResource(R.drawable.itachi);
                    break;
                case 2:
                    viveksimage.setImageResource(R.drawable.itachi2);
                    break;
                case 3:
                    viveksimage.setImageResource(R.drawable.luffy);
                    break;
                case 4:
                    viveksimage.setImageResource(R.drawable.naruto);
                    break;
            }
            c++;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viveksimage = (ImageView)findViewById(R.id.viveksimage);
        spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.music_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        vivekstext = (TextView)findViewById(R.id.vivekstext);
        enablebutton = (Button)findViewById(R.id.enablebutton);
        disablebutton = (Button)findViewById(R.id.disablebutton);
        viveksbutton = (Button)findViewById(R.id.viveksbutton);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        disablebutton.setEnabled(false);

    }
    public void onClick(View v)
    {
        c = k;
        stopslideshow =0;
        enablecheck =0;
        stoptimer = 0;
        enablebutton.setEnabled(true);


        Runnable s = new Runnable() {
            @Override
            public void run() {
                //long time = System.currentTimeMillis() + 10000;

                while(stopslideshow==0&&enablecheck == 0)
                {

                        synchronized (this) {
                            try {
                                wait(3000);
                            } catch (Exception e) {}

                        }
                        if(stopslideshow == 1 || enablecheck ==1) {
                            break;
                        }
                        else {
                            h.sendEmptyMessage(0);
                        }
                }
                if(stopslideshow == 1 || enablecheck == 1)
                {
                    stoptimer = 1;
                }
            }
        };

        Thread slideshow = new Thread(s);

        slideshow.start();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //long futuretime = System.currentTimeMillis() + 10000;
                while (stoptimer == 0)
                {
                    synchronized (this)
                    {
                        try {
                            wait(1000);

                        }catch(Exception e){}
                    }
                    if(stoptimer ==1)
                    {
                        break;
                    }
                    else{
                        handler.sendEmptyMessage(0);
                    }


                }





            }
        };
        Thread viveksthread = new Thread(r);
        viveksthread.start();







    }

    public void startbuttonClick(View v)
    {

        Intent i = new Intent(this,backgroundaudioservice.class);
        Intent j = new Intent(this,payphone.class);
        Toast.makeText(getApplicationContext(),String.valueOf(spinner.getSelectedItem()),Toast.LENGTH_LONG).show();

        if(Objects.equals(String.valueOf(spinner.getSelectedItem()),"demons"))
        {
            startService(i);
        }

        if(Objects.equals(String.valueOf(spinner.getSelectedItem()),"payphone"))
        {
            startService(j);

        }

    }

    public void onstopClick(View v)
    {
        Intent i =new Intent(this,backgroundaudioservice.class);
        Intent j = new Intent(this,payphone.class);
        stopService(i);
        stopService(j);



    }

    public void enable(View v)
    {

        enablebutton.setEnabled(false);
        disablebutton.setEnabled(true);
        viveksbutton.setEnabled(false);
        stopslideshow =1;
        enablecheck =1;
        stoptimer = 1;

        Runnable enablerun = new Runnable() {
            @Override
            public void run() {
                if(mSensor == null)
                {
                    Toast.makeText(getApplicationContext(),"No Proximity Sensor",Toast.LENGTH_LONG).show();

                }
                else
                {
                    mSensorManager.registerListener(proximitySensorListener,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
                }


            }
        };
        Thread sensor = new Thread(enablerun);
        sensor.start();

    }

    SensorEventListener proximitySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            k = c%5;

            if (event.values[0] == 0) {
                viveksimage.setImageResource(i[k]);
            }
            else
            {
                c++;
                viveksimage.setImageResource(i[k]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void disable(View v)
    {
        mSensorManager.unregisterListener(proximitySensorListener);
        disablebutton.setEnabled(false);
        viveksbutton.setEnabled(true);
        enablebutton.setEnabled(true);
    }
}
