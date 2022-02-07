package com.MyMADAPP.numad22sp_lakshmiposni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class ClickyClicky extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);

//        TextView pressedTxt = (TextView) findViewById(R.id.textView8);
//        pressedTxt.setText("Pressed: -  ");

//        Button btnA = findViewById(R.id.button3);
//        Button btnB = findViewById(R.id.button4);
//        Button btnC = findViewById(R.id.button5);
//        Button btnD = findViewById(R.id.button6);
//        Button btnE = findViewById(R.id.button7);
//        Button btnF = findViewById(R.id.button8);


    }

    public void onClick(View view){
        TextView pressedTxt = (TextView) findViewById(R.id.textView8);
//        pressedTxt.setText("Pressed: -  ");
        switch (view.getId()){
            case R.id.buttonA:
                pressedTxt.setText("Pressed: A");
                break;
            case R.id.buttonB:
                pressedTxt.setText("Pressed: B");
                break;
            case R.id.buttonC:
                pressedTxt.setText("Pressed: C");
                break;
            case R.id.buttonD:
                pressedTxt.setText("Pressed: D");
                break;
            case R.id.buttonE:
                pressedTxt.setText("Pressed: E");
                break;
            case R.id.buttonF:
                pressedTxt.setText("Pressed: F");
                break;
        }

    }


}