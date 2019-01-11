package com.bhx.library.network.annotation;

import com.bhx.library.network.bean.NetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 网络切换监听得注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NetWork {
    NetType type() default NetType.NONE;
}
