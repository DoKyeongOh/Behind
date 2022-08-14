package org.mytoypjt.utils;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.mytoypjt.service.annotation.Transaction;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;

public class TransactionManager {

    private static Connection connection;

    private TransactionManager() {
    }

    public static Connection getConnection(){
        return connection;
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
                    connection = DBUtil.getBasicDataSource().getConnection();
                    connection.setAutoCommit(false);

                    Object returnValue = methodProxy.invokeSuper(object, args);

                    connection.commit();
                    connection.close();

                    return returnValue;
                } catch (Exception e) {
                    e.printStackTrace();

                    connection.rollback();

                    if (connection != null)
                        connection.close();

                    return methodProxy.invokeSuper(object, args);
                }


            }
        };
        return ProxyUtil.getProxyInstance(aClass, methodInterceptor);
    }




}
