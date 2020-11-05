package com.github.cdtft.daotest.dao;

import com.github.cdtft.daotest.pojo.User;

import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年10月02日 11:10
 */
public interface UserMapper {

    List<User> findAll();

    User findOne(User user);
}
