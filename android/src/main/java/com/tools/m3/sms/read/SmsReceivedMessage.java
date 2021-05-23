package com.tools.m3.sms.read;

import android.database.Cursor;
import android.os.Build;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;
import java.util.Objects;

public class SmsReceivedMessage {
    SmsMessage smsMessage;
    Date received;


    String tag="";
    String message;
    String from;
    String to;


    public SmsReceivedMessage(SmsMessage smsMessage){
        this.smsMessage=smsMessage;

        this.message=smsMessage.getMessageBody();

        this.from=smsMessage.getOriginatingAddress();

        this.received= new Date(smsMessage.getTimestampMillis());

        StringBuilder sb = new StringBuilder();
        sb.append("getTimestampMillis" + smsMessage.getTimestampMillis());
        sb.append("getServiceCenterAddress" + smsMessage.getServiceCenterAddress());
        sb.append("getIndexOnIcc" + smsMessage.getIndexOnIcc());
        sb.append("getStatusOnIcc" + smsMessage.getStatusOnIcc());
        sb.append("getServiceCenterAddress" + smsMessage.getServiceCenterAddress());
        sb.append("getUserData" + new String(smsMessage.getUserData()));
        this.tag=sb.toString();
    }

    public SmsReceivedMessage() {

    }

    final static String TAG = "SmsReceivedMessage";


    public static SmsReceivedMessage createWith(Cursor cursor) {
        Log.i(TAG, "createWith: " +cursor.toString());
        final String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
        SmsReceivedMessage smsReceivedMessage = new  SmsReceivedMessage();
        smsReceivedMessage.setMessage(body);
        return smsReceivedMessage;
    }


    public SmsMessage getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(SmsMessage smsMessage) {
        this.smsMessage = smsMessage;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsReceivedMessage that = (SmsReceivedMessage) o;
        return received.equals(that.received) &&
                message.equals(that.message) &&
                from.equals(that.from);
    }

    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(received, message, from);
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append(received.toString());
            sb.append(message.toString());
            sb.append(from);
            return sb.toString().hashCode();
        }
    }

    @Override
    public String toString() {
        return "SmsReceivedMessage{" +
                "received=" + received +
                ", tag='" + tag + '\'' +
                ", message='" + message + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}

