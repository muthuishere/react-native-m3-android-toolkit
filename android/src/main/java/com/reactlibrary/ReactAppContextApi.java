package com.reactlibrary;

import android.content.Context;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.tools.m3.context.AppContextApi;

public class ReactAppContextApi implements AppContextApi {

    ReactApplicationContext reactContext;

    public ReactAppContextApi(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
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
