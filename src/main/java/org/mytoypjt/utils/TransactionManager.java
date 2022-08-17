package org.mytoypjt.utils;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.mytoypjt.service.annotation.Transaction;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;

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

                Connection connection = null;
                try {
//                    object.setAccessible(true);
                    Field[] fields = method.getDeclaringClass().getDeclaredFields();
                    Field connField = Arrays.stream(fields)
                            .filter(field -> field.getType() == Connection.class)
                            .findFirst().get();

                    if (connField == null)
                        return methodProxy.invokeSuper(object, args);

                    connField.setAccessible(true);
                    connField.set(object, DBUtil.getBasicDataSource().getConnection());


                    connection = (Connection) connField.get(object);
                    connection.setAutoCommit(false);

                    Object returnValue = methodProxy.invokeSuper(object, args);

                    connection.commit();
                    connection.close();

                    return returnValue;
                } catch (Exception e) {
                    e.printStackTrace();

                    try {
                        connection.rollback();
                        connection.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    return methodProxy.invokeSuper(object, args);
                }


            }
        };

        return ProxyUtil.getProxyInstance(aClass, methodInterceptor);
    }
}
