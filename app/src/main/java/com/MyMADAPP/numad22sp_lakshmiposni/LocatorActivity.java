package com.MyMADAPP.numad22sp_lakshmiposni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.xml.sax.Locator;

import java.util.List;
import java.util.Locale;

public class LocatorActivity extends AppCompatActivity implements LocationListener {

    Button button_location;
    LocationManager locationManager;
    TextView latitude;
    TextView longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);

        button_location = findViewById(R.id.button_location);
        latitude = findViewById(R.id.latOutput);
        longitude = findViewById(R.id.longOutput);

        if(ContextCompat.checkSelfPermission(LocatorActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LocatorActivity.this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);

        }

        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();

            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, LocatorActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onLocationChanged(Location location) {
        longitude.setText(String.valueOf(location.getLongitude()));
        latitude.setText(String.valueOf(location.getLatitude()));
    }

}















//        Toast.makeText(this, "Latitude: "+location.getLatitude() + " " + "Longitude: " + location.getLongitude(), Toast.LENGTH_LONG).show();
//
//        try{
//            Geocoder geocoder = new Geocoder(LocatorActivity.this, Locale.getDefault());
//            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            String address = addresses.get(0).getAddressLine(0);
//
//            textView_location.setText(address);
//        }catch(Exception e){
//            e.printStackTrace();
//        }