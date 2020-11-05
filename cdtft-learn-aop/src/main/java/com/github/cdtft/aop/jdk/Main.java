package com.github.cdtft.aop.jdk;

import com.github.cdtft.aop.UserDao;
import com.github.cdtft.aop.UserDaoInterface;

import java.lang.reflect.Proxy;

/**
 * @author : wangcheng
 * @date : 2020年10月15日 15:38
 */
public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        //需要传入被代理的对象，且该对象实现过接口。也就是说jdk是面向接口的动态代理
        UserDaoInterface proxyInstance = (UserDaoInterface) Proxy.newProxyInstance(UserDao.class.getClassLoader(), UserDao.class.getInterfaces()
                , new LogInvocationHandler(userDao));
        proxyInstance.findAll();
        proxyInstance.printUser();
    }
}
