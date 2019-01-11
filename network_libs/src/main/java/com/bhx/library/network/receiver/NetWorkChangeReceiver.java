package com.bhx.library.network.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.bhx.library.network.Constants;
import com.bhx.library.network.annotation.NetWork;
import com.bhx.library.network.bean.MethodManager;
import com.bhx.library.network.bean.NetType;
import com.bhx.library.network.util.NetUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 网络切换得广播监听
 */
public class NetWorkChangeReceiver extends BroadcastReceiver {

    private Map<Object, List<MethodManager>> mMaps;
    private NetType mNetType;
    private boolean hasNetWork;

    public NetWorkChangeReceiver() {
        mMaps = new HashMap<>();
        mNetType = NetType.NONE;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        if (intent.getAction().equals(Constants.ACTION_NETWORK_CHANGE)) {
            Log.i(Constants.LOG_TAG, "网络发生改变");
            if (NetUtils.isNetworkConnected()) {
                hasNetWork = true;
                Log.i(Constants.LOG_TAG, "网络已连接");
            } else {
                hasNetWork = false;
                Log.i(Constants.LOG_TAG, "网络已断开");
            }
            mNetType = NetUtils.getNetType();
            postEvent();
        }

    }

    /**
     * 分发事件
     */
    private void postEvent() {
        Set<Object> set = mMaps.keySet();
        for (Object object : set) {
            List<MethodManager> list = mMaps.get(object);
            if (list != null) {
                for (MethodManager manager : list) {
                    NetType type = manager.getType();
                    if (type.getClass().isAssignableFrom(mNetType.getClass()) && type == mNetType) {
                        invoke(object, manager.getMethod());
                    }
                }
            }
        }

    }

    /**
     * 调用注册类中加上NetWork注解得方法
     *
     * @param clazz
     * @param method
     */
    private void invoke(Object clazz, Method method) {
        try {
            method.invoke(clazz, mNetType);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册监听
     *
     * @param object
     */
    public void registerObserver(Object object) {
        List<MethodManager> list = mMaps.get(object);
        if (list == null) {
            list = findMethodAnnotation(object);
            mMaps.put(object, list);
        }
    }

    /**
     * 获取注解方法
     *
     * @param object
     */
    private List<MethodManager> findMethodAnnotation(Object object) {
        List<MethodManager> list = new ArrayList<>();
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            NetWork netWork = method.getAnnotation(NetWork.class);
            if (netWork == null) {
                continue;
            }
            Type type = method.getGenericReturnType();
            if (!"void".equals(type.toString())) {
                throw new RuntimeException(method.getName() + "返回值必须是Void得...........");
            }
            Type[] types = method.getGenericParameterTypes();
            Log.e(Constants.LOG_TAG, "参数类型:" + Arrays.toString(types));
            Class<?>[] classes = method.getParameterTypes();
            if (classes.length != 1) {
                throw new RuntimeException(method.getName() + "参数必须只有一个...........");
            }
            MethodManager manager = new MethodManager(classes[0], method, netWork.type());
            list.add(manager);
        }
        return list;
    }

    /**
     * 解注册
     *
     * @param object
     * @return
     */
    public boolean unRegisterObserver(Object object) {
        List<MethodManager> list = mMaps.get(object);
        if (list != null) {
            list.clear();
        }
        mMaps.remove(object);
        return mMaps.size() == 0;
    }
}
