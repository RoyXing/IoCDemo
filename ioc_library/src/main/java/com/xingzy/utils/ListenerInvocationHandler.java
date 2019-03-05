package com.xingzy.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author roy.xing
 * @date 2019/3/5
 */
public class ListenerInvocationHandler implements InvocationHandler {

    private Object target;
    private HashMap<String, Method> hashMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method != null) {
            String methodName = method.getName();
            method = hashMap.get(methodName);
            if (method != null) {
                return method.invoke(target, args);
            }
        }
        return null;
    }

    public void addMethod(String name, Method method) {
        hashMap.put(name, method);
    }
}
