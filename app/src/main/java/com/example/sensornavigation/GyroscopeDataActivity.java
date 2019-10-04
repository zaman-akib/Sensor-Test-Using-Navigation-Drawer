package com.example.sensornavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GyroscopeDataActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<Gyro> listData;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_data);

        setTitle("View Gyroscope Data");

        databaseReference = FirebaseDatabase.getInstance().getReference("GyroscopeData");

        listData= new ArrayList<>();
        customAdapter= new CustomAdapter(GyroscopeDataActivity.this,listData);

        listView = findViewById(R.id.listView);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listData.clear();

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Gyro gyro= dataSnapshot1.getValue(Gyro.class);
                    listData.add(gyro);
                }

                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
