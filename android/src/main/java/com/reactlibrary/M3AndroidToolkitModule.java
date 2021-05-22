package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.reactlibrary.shared.AppException;
import com.reactlibrary.context.ReactAppContextApi;
import com.reactlibrary.context.AppContextApi;
import com.reactlibrary.sim.SimService;
import com.reactlibrary.sms.read.ReadService;
import com.reactlibrary.sms.read.SmsOnReceiveService;
import com.reactlibrary.sms.read.SmsReceivedMessage;
import com.reactlibrary.sms.send.SendService;
import com.reactlibrary.sms.send.SendSmsRequest;
import com.reactlibrary.sms.send.SendSmsResult;

import java.util.List;

public class M3AndroidToolkitModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    final AppContextApi reactContextApi;
    private final SendService sendService;
    private final ReadService readService;
    private final SimService simService;
    private final SmsOnReceiveService smsOnReceiveService;

    public M3AndroidToolkitModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.reactContextApi = new ReactAppContextApi(reactContext);

        sendService=new SendService(reactContextApi);
        readService=new ReadService(reactContextApi);
        simService=new SimService(reactContextApi);
        smsOnReceiveService=new SmsOnReceiveService(reactContextApi);

    }

    @Override
    public String getName() {
        return "M3AndroidToolkit";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void sendSms(ReadableMap data,Callback success,Callback error){
        try {
            SendSmsResult sendSmsResult= sendService.sendSms(SendSmsRequest.createFrom(data));
            success.invoke(sendSmsResult);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void subscribeForSmsReceived(Callback success,Callback error){
        try {
            String sendSmsResult= smsOnReceiveService.subscribe();
            success.invoke(sendSmsResult);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }
    @ReactMethod
    public void unsubscribeForSmsReceived(Callback success,Callback error){
        try {
            String response= smsOnReceiveService.unsubscribe();
            success.invoke(response);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void readAllSms(Callback success,Callback error){
        try {
            List<SmsReceivedMessage> response= readService.getAllSms();
            success.invoke(response);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }


}
