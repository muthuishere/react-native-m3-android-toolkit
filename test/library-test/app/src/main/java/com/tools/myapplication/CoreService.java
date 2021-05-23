package com.tools.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.tools.m3.shared.AppException;
import com.tools.m3.sim.SimService;
import com.tools.m3.sms.read.ReadService;
import com.tools.m3.sms.read.SmsOnReceiveService;
import com.tools.m3.sms.send.SendService;
import com.tools.m3.sms.send.SendSmsRequest;
import com.tools.m3.sms.send.SendSmsResult;

import java.util.Optional;

public class CoreService {
    static final String TAG="CoreService";
    ActivityContextApi activityContextApi;
    private final SendService sendService;
    private final ReadService readService;
    private final SimService simService;
    private final SmsOnReceiveService smsOnReceiveService;
    ActivityLogger activityLogger =null;



    public CoreService(Context context,ActivityLogger activityLogger) {
        this.activityContextApi = new ActivityContextApi(context,activityLogger);
        this.activityLogger= activityLogger;
        sendService = new SendService(activityContextApi);
        readService = new ReadService(activityContextApi);
        simService = new SimService(activityContextApi);
        smsOnReceiveService = new SmsOnReceiveService(activityContextApi);
    }


    public void sendSms(SendSmsRequest sendSmsRequest){
        try {
            SendSmsResult sendSmsResult= sendService.sendSms(sendSmsRequest);
            logText("sendSms " + sendSmsResult.toString());
        }catch (AppException e){
            e.printStackTrace();
        }
    }


    public void subscribeForSmsReceived(){
        try {
            String sendSmsResult= smsOnReceiveService.subscribe();

            logText(sendSmsResult);
        }catch (AppException e){
            e.printStackTrace();
        }
    }

    public void unsubscribeForSmsReceived(){
        try {
            String response= smsOnReceiveService.unsubscribe();

        }catch (AppException e){
            e.printStackTrace();
        }
    }


    public void readAllSms(){
        try {
            logText(readService.getAllSms().toString()) ;

        }catch (AppException e){
            e.printStackTrace();
        }

    }


    public void getAllSims(){
        try {
            logText(simService.getAllSims().toString()) ;

        }catch (AppException e){
            e.printStackTrace();
        }

    }

    public void logText(String msg){
        Log.i(TAG, "logText: " + msg);
        if(null != activityLogger)
            activityLogger.logText(msg);
    }

}
