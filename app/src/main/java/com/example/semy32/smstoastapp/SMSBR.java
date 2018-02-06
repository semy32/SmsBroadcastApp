package com.example.semy32.smstoastapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by semy32 on 2/6/2018.
 */

public class SMSBR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] sms;
        String felado = "";
        String uzenet = "";


        if (intent.getExtras()!= null){
            Object[] pdus = (Object[]) bundle.get("pdus");

            sms = new SmsMessage[pdus.length];

            for (int i = 0; i < pdus.length; i++) {
                sms[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                //api level 15 nem támogatja a createFromPdu((byte[]) pdus[i],format);-ot

                if (i==0){
                    felado = sms[i].getOriginatingAddress();
                }
                uzenet = uzenet + sms[i].getMessageBody();
            }
            Toast.makeText(context,"Feladó: " + felado + " Üzenet: " + uzenet, Toast.LENGTH_LONG).show();
        }

        abortBroadcast();

        }


    }

