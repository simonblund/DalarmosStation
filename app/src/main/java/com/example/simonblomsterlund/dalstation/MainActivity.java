package com.example.simonblomsterlund.dalstation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button openSetupButton;
    TextView textAlarmphoneSet;
    TextView textserverSet;
    TextView setupDalarmos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("dalStatPrefs", Context.MODE_PRIVATE);
        String alarmphone = sharedPref.getString("alarmphone", "");
        String serverurl = sharedPref.getString("serverurl", "");

        setupDalarmos = (TextView) findViewById(R.id.textView_setupDalarmos);
        textAlarmphoneSet = (TextView) findViewById(R.id.text_alarmphoneSet);
        textserverSet = (TextView) findViewById(R.id.text_serverSet);
        if (!alarmphone.isEmpty()){
            textAlarmphoneSet.setText(alarmphone);
        }
        if (!serverurl.isEmpty()){
            textserverSet.setText(serverurl);
        }
        if(serverurl.isEmpty() || alarmphone.isEmpty()){
            setupDalarmos.setVisibility(View.VISIBLE);
        }
        openSetupButton = (Button) findViewById(R.id.openSetup);
        openSetupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent setupIntent = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(setupIntent);
            }
        });
    }


}
