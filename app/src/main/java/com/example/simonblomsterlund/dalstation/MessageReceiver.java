package com.example.simonblomsterlund.dalstation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by simonblomsterlund on 16/06/2017.
 */

public class MessageReceiver extends BroadcastReceiver {


        public static final String SMS_BUNDLE = "pdus";

        public void onReceive(Context context, Intent intent) {
            Bundle intentExtras = intent.getExtras();

            if (intentExtras != null) {
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);

                String smsMessageStr = "";
                for (int i = 0; i < sms.length; ++i) {

                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[0]);

                    String smsBody = smsMessage.getMessageBody().toString();
                    String address = smsMessage.getOriginatingAddress();

                    smsMessageStr += address + "\n";
                    smsMessageStr += smsBody + "\n";
                }

                Toast.makeText(context, "Message Received!", Toast.LENGTH_SHORT).show();

                if (MainActivity.active) {

                } else {
                    Intent i = new Intent(context, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            }
        }

}
