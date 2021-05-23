package com.tools.m3.sms.send;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;

import com.tools.m3.context.AppContextApi;
import com.tools.m3.sms.shared.SmsManagerService;

import java.util.List;

public class SendService {


     AppContextApi reactContextApi;

    SmsSendEventHandler smsSendEventHandler;
    SmsManagerService smsManagerService;

Context context;
    private static String SENT = "SMS_SENT", DELIVERED = "SMS_DELIVERED";

    public SendService(AppContextApi reactContextApi) {
        this.reactContextApi = reactContextApi;
        smsSendEventHandler = new  SmsSendEventHandler(reactContextApi);
        smsManagerService = new SmsManagerService(reactContextApi);
        context=reactContextApi.getContext();
    }



    PendingIntent sentPI =null;
    PendingIntent deliveredPI =null;

    static boolean initialized=true;
    List<SmsManager> smsManagers =null;

    public void initForSend(){

        if(initialized ) {
             sentPI = PendingIntent.getBroadcast(context, 0, new Intent(SENT), 0);

             deliveredPI = PendingIntent.getBroadcast(context, 0,
                    new Intent(DELIVERED), 0);

            context.registerReceiver(smsSendEventHandler, new IntentFilter(SENT));

            smsManagerService.initSmsManagers();


            initialized=false;
        }

    }



    public final SendSmsResult sendSms(final SendSmsRequest sendSmsRequest){
        initForSend();
        SmsManager smsManager = smsManagerService.getAt(sendSmsRequest.getSimIndex());
        smsManager.sendTextMessage(sendSmsRequest.getMobileNumber(), null, sendSmsRequest.getMessage(), sentPI, deliveredPI);
        return new SendSmsResult("Successfully Sent sms  for processing");
    }
}
