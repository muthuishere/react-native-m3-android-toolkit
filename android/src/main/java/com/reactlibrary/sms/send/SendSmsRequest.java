package com.reactlibrary.sms.send;

import com.facebook.react.bridge.ReadableMap;

public class SendSmsRequest {

     String mobileNumber;

    public static SendSmsRequest createFrom(ReadableMap data) {

        SendSmsRequest sendSmsRequest=new SendSmsRequest();
        sendSmsRequest.message=data.getString("message");
        sendSmsRequest.mobileNumber=data.getString("mobileNumber");
        sendSmsRequest.simIndex=data.getInt("simIndex");
        return sendSmsRequest;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSimIndex() {
        return simIndex;
    }

    public void setSimIndex(int simIndex) {
        this.simIndex = simIndex;
    }

    String message;
     int simIndex ;
}

