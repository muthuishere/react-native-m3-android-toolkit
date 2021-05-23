package com.tools.m3.sms.send;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import com.tools.m3.context.AppContextApi;

public class SmsSendEventHandler extends BroadcastReceiver {


    private AppContextApi reactContextApi;


    public SmsSendEventHandler(AppContextApi reactContextApi) {
        this.reactContextApi = reactContextApi;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                reactContextApi.emit("sms:send-notification", "Successfully Sent SMS");
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                reactContextApi.emit("sms:error-notification", "RESULT_ERROR_GENERIC_FAILURE");

                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                reactContextApi.emit("sms:error-notification", "RESULT_ERROR_NO_SERVICE");
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                reactContextApi.emit("sms:error-notification", "RESULT_ERROR_NULL_PDU");
                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                reactContextApi.emit("sms:error-notification", "RESULT_ERROR_RADIO_OFF");
                break;
        }
    }





}
