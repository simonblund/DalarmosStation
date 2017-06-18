package com.example.simonblomsterlund.dalstation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by simonblomsterlund on 16/06/2017.
 */

public class MessageReceiver extends BroadcastReceiver {
    @Override




    public void onReceive(Context context, Intent intent) {
        // get the Bundle map from the Intent parameter to onReceive()
        Bundle bundle = intent.getExtras();

// get the SMS received
        Object[] pdus = (Object[]) bundle.get("pdus");
        assert pdus != null;
        SmsMessage[] msgs = new SmsMessage[pdus.length];

        //sms sender phone
        String smsSender = "";

        //body of received sms
        String smsBody = "";

        long timestamp = 0L;

        for (int i = 0; i < msgs.length; i++) {
            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            smsSender += msgs[i].getOriginatingAddress();
            smsBody += msgs[i].getMessageBody();
            timestamp += msgs[i].getTimestampMillis();
        }
        SharedPreferences sharedPref = context.getSharedPreferences("dalStatPrefs", Context.MODE_PRIVATE);
        String alarmphone = sharedPref.getString("alarmphone", "");

        if (smsSender == alarmphone){
            Toast.makeText(context, smsBody + alarmphone, Toast.LENGTH_LONG);
        }
    }
}
