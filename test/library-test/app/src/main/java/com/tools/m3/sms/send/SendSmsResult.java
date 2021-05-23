package com.tools.m3.sms.send;

public class SendSmsResult {

    public String getResponse() {
        return response;
    }

    private String response;
    public SendSmsResult(String response) {

        this.response =response;
    }

    @Override
    public String toString() {
        return "SendSmsResult{" +
                "response='" + response + '\'' +
                '}';
    }
}
