package com.example.sensornavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent intent;

        if(id == R.id.accelerometer){
            intent = new Intent(this,AccelerometerActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.gyroscope){
            intent = new Intent(this,GyroscopeActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.proximity){
            intent = new Intent(this,ProximityAcitvity.class);
            startActivity(intent);
        }
        else if(id == R.id.shake){
            intent = new Intent(this,ShakeActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.map){
            intent = new Intent(this,MapActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.gyro_data){
            intent = new Intent(this,GyroscopeDataActivity.class);
            startActivity(intent);
        }

        return true;
    }
}
