package com.benbaba.socket_libs.method;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储对象中得方法，进行反射调用
 */
public class MethodManager {
    private Map<Object, List<MethodBean>> mMethodMaps;//

    public MethodManager() {
        mMethodMaps = new HashMap<>();
    }

    /**
     * 注册方法
     *
     * @param obj
     */
    public void register(@NonNull Object obj) {
        List<MethodBean> methods = mMethodMaps.get(obj);
        if (methods == null) {
            methods = new ArrayList<>();

        }
    }

    /**
     * 遍历obj类得对象获取带有@ReceiveSocketMsg 注解得方法 并存储消息类型
     *
     * @param obj  类对象
     * @param list 需要push得List
     */
    private void pushMethods(Object obj, List<MethodBean> list) {

    }

    /**
     * 取消注册
     *
     * @param obj
     */
    public void unRegister(@NonNull Object obj) {

    }

}
