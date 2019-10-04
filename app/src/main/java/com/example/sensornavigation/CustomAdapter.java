package com.example.sensornavigation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Gyro> {
    private Activity context;
    private List<Gyro> dataList;

    public CustomAdapter(Activity context, List<Gyro> dataList) {
        super(context, R.layout.show_data_layout, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater= context.getLayoutInflater();
        View view= layoutInflater.inflate(R.layout.show_data_layout,null,true);

        Gyro gyro=dataList.get(position);

        TextView xGyro;

        xGyro= view.findViewById(R.id.xGyro);

        xGyro.setText("X: "+gyro.getxGyro()+" | Y: "+gyro.getyGyro()+" | Z: "+gyro.getzGyro());

        return view;
    }
}
