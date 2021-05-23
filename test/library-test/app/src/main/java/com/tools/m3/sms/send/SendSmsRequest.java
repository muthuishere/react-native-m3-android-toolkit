package com.tools.m3.sms.send;



public class SendSmsRequest {

     String mobileNumber;

    public SendSmsRequest() {
    }

    public SendSmsRequest(String mobileNumber, String message, int simIndex) {
        this.mobileNumber = mobileNumber;
        this.message = message;
        this.simIndex = simIndex;
    }

    public SendSmsRequest(String mobileNumber, String message) {
        this.mobileNumber = mobileNumber;
        this.message = message;
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
     int simIndex=0 ;
}

