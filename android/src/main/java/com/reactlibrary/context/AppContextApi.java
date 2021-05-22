package com.reactlibrary.context;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;

public interface AppContextApi {


    Context getContext();

    void emit(String key, Object value);


}
