package com.github.cdtft.aop;

/**
 * @author : wangcheng
 * @date : 2020年10月15日 15:08
 */
public class UserDao implements UserDaoInterface {

    @Override
    public void findAll() {
        System.out.println("查找所有的用户");
    }

    @Override
    public void printUser() {
        System.out.println("这里有个用户");
    }

}
