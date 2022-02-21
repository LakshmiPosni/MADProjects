package com.MyMADAPP.numad22sp_lakshmiposni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutMeBTN = findViewById(R.id.button);
        Button clickyClicky = findViewById(R.id.button2);
        Button linkCollector = findViewById(R.id.button3);
        Button locator = findViewById(R.id.locatorBtn);

//        aboutMeBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Lakshmi Posni (posni.l@northeastern.edu)", Toast.LENGTH_SHORT).show();
//            }
//        });

        aboutMeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutMe();
            }
        });

        clickyClicky.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                clickyClicky();
            }
        });

        linkCollector.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                linkCollector();
            }
        });

        locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locator();
            }
        });
    }

    private void aboutMe(){
        Intent intent = new Intent(this, AboutMe.class);
        startActivity(intent);
    }

    private void clickyClicky(){
        Intent intent = new Intent(this, ClickyClicky.class);
        startActivity(intent);
    }

    private void linkCollector(){
        Intent intent = new Intent(this, ItemActivity.class);
        startActivity(intent);
    }

    private void locator(){
        Intent intent = new Intent(this, LocatorActivity.class);
        startActivity(intent);
    }

}