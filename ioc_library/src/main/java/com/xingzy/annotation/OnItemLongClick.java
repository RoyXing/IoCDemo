package com.xingzy.annotation;

import com.xingzy.recyclerview.RView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author roy.xing
 * @date 2019/3/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnItemLongClickListener", listenerType = RView.OnItemLongClickListener.class, callBackListener = "onItemLongClick")
public @interface OnItemLongClick {
    int[] value();
}
