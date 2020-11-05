package com.github.cdtft.aop.jdk;

import com.github.cdtft.aop.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : wangcheng
 * @date : 2020年10月15日 15:34
 */
public class LogInvocationHandler implements InvocationHandler {

    /**
     * 代理的对象
     */
    private final UserDao userDao;

    public LogInvocationHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(method.getName());
        Object invoke = method.invoke(userDao, args);
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
