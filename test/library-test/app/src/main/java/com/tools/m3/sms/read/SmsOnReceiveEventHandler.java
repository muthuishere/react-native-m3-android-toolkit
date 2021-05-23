package com.tools.m3.sms.read;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.google.gson.Gson;
import com.tools.m3.context.AppContextApi;

import java.util.ArrayList;
import java.util.List;

public class SmsOnReceiveEventHandler extends BroadcastReceiver {


    static final String TAG = "SmsOnReceiveHandler";

    AppContextApi reactContext;

    SmsCache smsCache = null;
    static Gson gson = new Gson();
    ;

    public SmsOnReceiveEventHandler(AppContextApi reactContext) {
        this.reactContext = reactContext;
        this.smsCache = SmsCache.getInstance();

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, " onReceive getResultCode()" + getResultCode());
        reactContext.emit("sms:received", getMessagesAsJson(intent));

//
//        switch (getResultCode()) {
//            case Activity.RESULT_OK:
//                sendEvent("sms:send-notification", "Successfully Sent SMS");
//                break;
//            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                sendEvent("sms:error-notification", "RESULT_ERROR_GENERIC_FAILURE");
//
//                break;
//            case SmsManager.RESULT_ERROR_NO_SERVICE:
//                sendEvent("sms:error-notification", "RESULT_ERROR_NO_SERVICE");
//                break;
//            case SmsManager.RESULT_ERROR_NULL_PDU:
//                sendEvent("sms:error-notification", "RESULT_ERROR_NULL_PDU");
//                break;
//            case SmsManager.RESULT_ERROR_RADIO_OFF:
//                sendEvent("sms:error-notification", "RESULT_ERROR_RADIO_OFF");
//                break;
//        }


    }


    final String getMessagesAsJson(Intent intent) {

        return gson.toJson(getMessages(intent));
    }

    private List<SmsReceivedMessage> getMessages(Intent intent) {
        final Bundle bundle = intent.getExtras();
        final List<SmsReceivedMessage> results = new ArrayList<>();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                if (pdusObj != null) {
                    for (Object aPdusObj : pdusObj) {

                        SmsReceivedMessage e = new SmsReceivedMessage(SmsMessage.createFromPdu((byte[]) aPdusObj));
                        if (smsCache.isNotPresent(e)) {
                            results.add(e);
                            smsCache.add(e);
                        }
//            message = currentMessage.getDisplayMessageBody();
//            address = currentMessage.getDisplayOriginatingAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//    content = "{ \"address\": \"" + address + "\", \"message\": \"" + message + "\"}";
        return results;
    }


}
