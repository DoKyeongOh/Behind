package org.mytoypjt.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class ProxyUtil {

    public ProxyUtil() {
    }

    public static Object getProxyInstance(Class aClass, MethodInterceptor methodInterceptor){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(aClass);
        enhancer.setCallback(methodInterceptor);
        return enhancer.create();
    }

}
