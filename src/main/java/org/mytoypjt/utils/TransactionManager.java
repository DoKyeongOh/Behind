package org.mytoypjt.utils;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.mytoypjt.service.annotation.Transaction;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TransactionManager {

    private TransactionManager() {
    }

    public static Object getInstance(Class aClass){
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                int annotationCount = (int) Arrays.stream(method.getAnnotations())
                        .filter(annotation -> annotation instanceof Transaction).count();

                if (annotationCount < 1)
                    return methodProxy.invokeSuper(object, args);


                try {
                    // 커넥션 풀에서 커넥션 받아오기
                    Field[] fields = method.getDeclaringClass().getDeclaredFields();
                    Field connectionField = Arrays.stream(fields)
                            .filter(field -> field.getType() == Connection.class)
                            .findFirst().get();

                    connectionField.setAccessible(true);
                    connectionField.set(object, DBUtil.getConnection());

                    Connection connection = (Connection) connectionField.get(object);

                    Object returnValue = methodProxy.invokeSuper(object, args);

                    // 커밋하기

                    return returnValue;
                } catch (Exception e) {
                    // 롤백하기
                    e.printStackTrace();
                    return methodProxy.invokeSuper(object, args);
                }
            }
        };

        return ProxyUtil.getProxyInstance(aClass, methodInterceptor);
    }



}
