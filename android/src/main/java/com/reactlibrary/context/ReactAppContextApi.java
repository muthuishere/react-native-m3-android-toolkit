package com.reactlibrary.context;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.reactlibrary.context.AppContextApi;
import com.reactlibrary.shared.AppException;
import com.reactlibrary.sms.read.SmsReceivedMessage;

import java.util.ArrayList;
import java.util.List;

public class ReactAppContextApi implements AppContextApi {

    ReactApplicationContext reactContext;

    public ReactAppContextApi(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
    }


    @Override
    public  Cursor getSmsReadCursor(){

        Uri mSmsQueryUri = Uri.parse("content://sms/inbox");
        List<SmsReceivedMessage> messages = new ArrayList<>();
        Cursor cursor = null;

        cursor = reactContext.getContentResolver().query(mSmsQueryUri, null, null, null, null);
        if (cursor == null) {
            Log.i(null, "cursor is null. uri: " + mSmsQueryUri);
            throw new AppException("cursor is null. uri: " + mSmsQueryUri);
        }

        return cursor;
    }

    @Override
    public Context getContext() {

        return reactContext;
    }

    @Override
    public void emit(String key, Object value) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(key,value);

    }


}
