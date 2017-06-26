package com.example.simonblomsterlund.dalstation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static boolean active = false;
    static  Activity inst;
    @Override
    public void onStart() {
        super.onStart();
        active = true;
        inst = this;
    }
    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;

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
        if (!alarmphone.isEmpty()) {
            textAlarmphoneSet.setText(alarmphone);
        }
        if (!serverurl.isEmpty()) {
            textserverSet.setText(serverurl);
        }
        if (serverurl.isEmpty() || alarmphone.isEmpty()) {
            setupDalarmos.setVisibility(View.VISIBLE);
        }
        openSetupButton = (Button) findViewById(R.id.openSetup);
        openSetupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setupIntent = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(setupIntent);
            }
        });
    }

    public void getPermissionToReadSMS() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_SMS)) {
                    Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.READ_SMS},
                        READ_SMS_PERMISSIONS_REQUEST);
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode,
        @NonNull String permissions[],
        @NonNull int[] grantResults) {
            // Make sure it's our original READ_CONTACTS request
            if (requestCode == READ_SMS_PERMISSIONS_REQUEST) {
                if (grantResults.length == 1 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
                }

            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }}





