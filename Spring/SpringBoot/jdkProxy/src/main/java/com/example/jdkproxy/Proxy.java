package com.example.jdkproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Proxy implements JdkProxyApplication.Foo {
    JdkProxyApplication.InvocationHandler h;
    Proxy(JdkProxyApplication.InvocationHandler h){
        this.h=h;
    }
    @Override
    public void foo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method foo= JdkProxyApplication.Foo.class.getMethod("foo");
        h.invoke(foo, new Object[0]);//forward call
    }
}
