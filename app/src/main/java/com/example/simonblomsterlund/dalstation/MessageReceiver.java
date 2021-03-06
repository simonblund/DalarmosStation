package com.example.simonblomsterlund.dalstation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;



/**
 * Created by simonblomsterlund on 16/06/2017.
 */

public class MessageReceiver extends BroadcastReceiver {


        public static final String SMS_BUNDLE = "pdus";


        public void onReceive(Context context, Intent intent) {
            SharedPreferences sharedPref = context.getSharedPreferences("dalStatPrefs", Context.MODE_PRIVATE);
            String alarmphone = sharedPref.getString("alarmphone", "");

            Bundle intentExtras = intent.getExtras();

            if (intentExtras != null) {
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);

                String message = "";
                String smsMessageSender = "";


                for (int i = 0; i < sms.length; ++i) {

                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[0]);

                    String smsBody = smsMessage.getMessageBody().toString();
                    String sender = smsMessage.getOriginatingAddress();

                    smsMessageSender += sender;
                    message += smsBody;
                }

                if (alarmphone.equals(smsMessageSender)) {
                    incidentMakerUtil incident = new incidentMakerUtil();
                    incident.parametrise(message, context);
                }
                else {
                    Toast.makeText(context, message + "from else on receiver", Toast.LENGTH_SHORT).show();
                }







                if (MainActivity.active) {

                } else {
                    Intent i = new Intent(context, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            }
        }

}
