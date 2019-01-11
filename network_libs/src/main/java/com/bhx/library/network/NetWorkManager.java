package com.bhx.library.network;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import com.bhx.library.network.receiver.NetWorkChangeReceiver;

public class NetWorkManager {

    private static volatile NetWorkManager mInstance;
    private NetWorkChangeReceiver mReceiver;
    private Application mApplication;

    private NetWorkManager() {
        mReceiver = new NetWorkChangeReceiver();
    }

    public static NetWorkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkManager.class) {
                if (mInstance == null)
                    mInstance = new NetWorkManager();
            }
        }
        return mInstance;
    }

    /**
     * 在Application进行初始化
     *
     * @param application
     */
    public void init(Application application) {
        mApplication = application;
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_NETWORK_CHANGE);
        application.registerReceiver(mReceiver, filter);
    }

    /**
     * 在任意类中去注册
     *
     * @param object
     */
    public void registerObserver(Object object) {
        mReceiver.registerObserver(object);
    }

    public Context getApplication() {
        if (mApplication == null) {
            throw new RuntimeException("请在Application中init......");
        }
        return mApplication;
    }

    /**
     * 在任意类中去解绑
     *
     * @param object
     */
    public void unRegisterObserver(Object object) {
        //是否全部解注册
        boolean isClear = mReceiver.unRegisterObserver(object);
        if (isClear) {
            mApplication.unregisterReceiver(mReceiver);
        }
    }
}
