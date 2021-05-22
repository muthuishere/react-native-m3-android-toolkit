package com.reactlibrary.sms.send;

public class SendSmsResult {

    public String getResponse() {
        return response;
    }

    private String response;
    public SendSmsResult(String response) {

        this.response =response;
    }
}
