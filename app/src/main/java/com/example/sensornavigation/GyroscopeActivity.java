package com.example.sensornavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GyroscopeActivity extends AppCompatActivity implements SensorEventListener {
    private TextView xGyro,yGyro,zGyro;
    private Sensor mySensor;
    private SensorManager SM;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        setTitle("Gyroscope Sensor Test");

        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor=SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        xGyro=findViewById(R.id.xGyro);
        yGyro=findViewById(R.id.yGyro);
        zGyro=findViewById(R.id.zGyro);

        databaseReference = FirebaseDatabase.getInstance().getReference("GyroscopeData");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        xGyro.setText("X: " + (int)x);
        yGyro.setText("Y: " + (int)y);
        zGyro.setText("Z: " + (int)z);

        if((int)x != 0 || (int)y!= 0 || (int)z != 0){
            String key = databaseReference.push().getKey();

            Gyro gyro = new Gyro(String.valueOf((int)x),String.valueOf((int)y),String.valueOf((int)z));

            databaseReference.child(key).setValue(gyro);

            Toast.makeText(GyroscopeActivity.this,"New data is saved",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);    }
    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }
}
