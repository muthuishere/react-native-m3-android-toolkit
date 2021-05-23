package com.tools.m3.context;

import android.content.Context;

public interface AppContextApi {


    Context getContext();

    void emit(String key, Object value);


}
