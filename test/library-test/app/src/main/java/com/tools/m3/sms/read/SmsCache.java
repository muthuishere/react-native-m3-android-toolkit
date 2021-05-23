package com.tools.m3.sms.read;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SmsCache {

    private static SmsCache instance = new SmsCache(20);

    int bufferSize;

    private SmsCache(int bufferSize) {
        this.bufferSize = bufferSize;
    }


    public static SmsCache getInstance() {

        return instance;
    }

    Queue<Integer> messages = new LinkedBlockingQueue<>();


    public void add(Object message) {

        if (messages.size() > bufferSize)
            messages.remove();

        messages.add(message.hashCode());
    }

    public boolean isNotPresent(Object smsReceivedMessage) {

        return !messages.contains(smsReceivedMessage.hashCode());
    }
}
