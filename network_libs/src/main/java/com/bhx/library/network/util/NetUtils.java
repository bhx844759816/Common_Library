package com.bhx.library.network.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bhx.library.network.NetWorkManager;
import com.bhx.library.network.bean.NetType;

@SuppressLint("MissingPermission")
public class NetUtils {
    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) NetWorkManager.getInstance().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 获取当前得网络类型
     *
     * @param context
     * @return
     */
    public static NetType getNetType() {
        // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) NetWorkManager.getInstance().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) return NetType.NONE;
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) return NetType.NONE;
        int type = info.getType();
        if (type == ConnectivityManager.TYPE_MOBILE) {
            if (info.getExtraInfo().toLowerCase().equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;

    }
}
