package com.bhx.library.network.bean;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 方法对象
 */
public class MethodManager {

    private Class<?> clazz; //方法所属得类
    private Method method; // 方法名
    private NetType type;// 方法参数

    public MethodManager(Class<?> clazz, Method method, NetType type) {
        this.clazz = clazz;
        this.method = method;
        this.type = type;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public NetType getType() {
        return type;
    }

    public void setType(NetType type) {
        this.type = type;
    }
}


