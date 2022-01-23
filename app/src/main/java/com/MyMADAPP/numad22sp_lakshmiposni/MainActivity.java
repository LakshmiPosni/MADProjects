package com.MyMADAPP.numad22sp_lakshmiposni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutMeBTN = findViewById(R.id.button);

        aboutMeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutMe();
            }
        });
    }

    private void aboutMe(){
        Intent intent = new Intent(this, AboutMe.class);
        startActivity(intent);
    }
}