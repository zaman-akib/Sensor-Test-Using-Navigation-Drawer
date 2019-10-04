package com.example.sensornavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener{
    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        setTitle("Accelerometer Sensor Test");

        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SM.registerListener((SensorEventListener) this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        xText=findViewById(R.id.xtext);
        yText=findViewById(R.id.ytext);
        zText=findViewById(R.id.ztext);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int x = (int)(sensorEvent.values[0]);
        int y = (int)(sensorEvent.values[1]);
        int z = (int)(sensorEvent.values[2]);

        //do what you want with the sensor values here. Perform some actions, change activity, call, toast etc.
        xText.setText("X: " + x);
        yText.setText("Y: " + y);
        zText.setText("Z: " + z);


        if (y >= 9) {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            Toast toast = Toast.makeText(getApplicationContext(), "LEFT", Toast.LENGTH_SHORT); toast.show();
        }
        else if(y <=- 9) {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            Toast toast = Toast.makeText(getApplicationContext(), "RIGHT", Toast.LENGTH_SHORT); toast.show();
        }
        else {
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume() {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);    }

    @Override
    protected void onPause() {
        super.onPause();
        SM.unregisterListener(this);
    }
}