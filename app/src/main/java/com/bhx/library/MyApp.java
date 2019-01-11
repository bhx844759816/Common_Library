package com.bhx.library;

import android.app.Application;

import com.bhx.library.network.NetWorkManager;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        NetWorkManager.getInstance().init(this);
    }
}
