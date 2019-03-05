package com.xingzy;

import android.app.Activity;
import android.view.View;

import com.xingzy.annotation.ContentView;
import com.xingzy.annotation.EventBase;
import com.xingzy.annotation.ViewInject;
import com.xingzy.utils.ListenerInvocationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author roy.xing
 * @date 2019/3/5
 */
public class InjectManager {

    public static void inject(Activity activity) {

        injectContentView(activity);

        injectViews(activity);

        injectEvents(activity);

    }

    public static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        ContentView annotation = clazz.getAnnotation(ContentView.class);
        if (annotation != null) {
            int layoutId = annotation.value();
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ViewInject annotation = field.getAnnotation(ViewInject.class);
            if (annotation != null) {
                int viewId = annotation.value();
                try {
                    Method method = clazz.getMethod("findViewById", int.class);
                    Object value = method.invoke(activity, viewId);
                    field.setAccessible(true);
                    field.set(activity, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void injectEvents(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //获取注解上的注解
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    if (eventBase != null) {
                        String listenerSetter = eventBase.listenerSetter();
                        Class<?> listenerType = eventBase.listenerType();
                        String callBackListener = eventBase.callBackListener();

                        try {
                            //通过annotationType获取onClick注解的value值
                            Method annotationTypeMethod = annotationType.getMethod("value");
                            //执行value方法 值获取注解的的值
                            int[] viewIds = (int[]) annotationTypeMethod.invoke(annotation);

                            ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                            handler.addMethod(callBackListener, method);

                            //ClassLoader loader:指定当前目标对象使用类加载器，获取加载器的方法是固定的
                            //Class<?>[] interfaces:目标对象实现的接口的类型，使用泛型方式确认类型
                            //InvocationHandler h:事件处理，执行目标对象的方法时，会触发事件处理器的方法
                            Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                    new Class[]{listenerType}, handler);
                            for (int viewId : viewIds) {
                                Method viewByIdMethod = clazz.getMethod("findViewById", int.class);
                                View view = (View) viewByIdMethod.invoke(activity, viewId);
                                Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                                setter.invoke(view, listener);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
