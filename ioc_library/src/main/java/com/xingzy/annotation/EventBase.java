package com.xingzy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author roy.xing
 * @date 2019/3/5
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {

    String listenerSetter();

    Class<?> listenerType();

    String callBackListener();
}
