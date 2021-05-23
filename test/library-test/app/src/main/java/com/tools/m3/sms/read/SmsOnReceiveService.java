package com.tools.m3.sms.read;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.tools.m3.shared.AppException;
import com.tools.m3.context.AppContextApi;

public class SmsOnReceiveService {


    Context  context;
    SmsOnReceiveEventHandler smsOnReceiveEventHandler;



    public SmsOnReceiveService(AppContextApi reactContextApi) {

        this.context = reactContextApi.getContext();
        smsOnReceiveEventHandler = new  SmsOnReceiveEventHandler(reactContextApi);

    }


    public String subscribe(){

        try {

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {

                String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
                context.registerReceiver(smsOnReceiveEventHandler, new IntentFilter(SMS_RECEIVED_ACTION));

                Log.i(this.getClass().getCanonicalName(), "subscribe: read sms success ");
            } else {
               throw new Exception("Required RECEIVE_SMS and READ_SMS permission");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.toString());
        }

        return "subscribe: read sms success ";
    }

    public String unsubscribe() {
        try {
            if (smsOnReceiveEventHandler != null) {
                context.unregisterReceiver(smsOnReceiveEventHandler);

                Log.i(this.getClass().getCanonicalName(), "unsubscribe: read sms success ");
            }

        } catch (Exception e) {
            e.printStackTrace();
           throw new AppException("Error" + e.getMessage());
        }
        return "unsubscribe: read sms success ";
    }

}
