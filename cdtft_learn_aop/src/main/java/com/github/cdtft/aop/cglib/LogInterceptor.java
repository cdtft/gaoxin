package com.github.cdtft.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : wangcheng
 * @date : 2020年10月15日 15:13
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before(method.getName());
        Object invoke = methodProxy.invokeSuper(o, objects);
        after(method.getName());
        return invoke;
    }

    private void before(String methodName) {
        System.out.println("调用方法" + methodName + "之【前】执行日志");
    }

    private void after(String methodName) {
        System.out.println("调用方法" + methodName + "之【后】执行日志");
    }
}
