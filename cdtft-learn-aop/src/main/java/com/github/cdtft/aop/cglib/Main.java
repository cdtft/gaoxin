package com.github.cdtft.aop.cglib;

import com.github.cdtft.aop.UserDao;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author : wangcheng
 * @date : 2020年10月15日 15:21
 */
public class Main {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);
        enhancer.setCallback(new LogInterceptor());
        UserDao userDao = (UserDao) enhancer.create();
        userDao.findAll();
        userDao.printUser();
    }
}
