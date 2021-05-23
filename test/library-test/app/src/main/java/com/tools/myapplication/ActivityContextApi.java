package com.tools.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.tools.m3.context.AppContextApi;
import com.tools.m3.shared.AppException;
import com.tools.m3.sim.SimDetail;
import com.tools.m3.sms.read.SmsReceivedMessage;
import com.tools.m3.sms.send.SendSmsRequest;
import com.tools.m3.sms.send.SendSmsResult;

import java.util.List;

public class ActivityContextApi implements AppContextApi {

    static final String TAG="ActivityContextApi";
    ActivityLogger activityLogger=null;
    Context context;


    public ActivityContextApi(Context context) {
        this.context = context;
    }

    public ActivityContextApi(Context context, ActivityLogger activityLogger) {
        this.context = context;
        this.activityLogger = activityLogger;

    }


    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void emit(String key, Object value) {

        String msg = "emit: " + key + "value" + value;
        Log.i(TAG, msg);

        activityLogger.logText(msg);

    }


}
