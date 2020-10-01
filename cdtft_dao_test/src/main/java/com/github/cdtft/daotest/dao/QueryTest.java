package com.github.cdtft.daotest.dao;

import com.github.cdtft.dao.core.SqlSession;
import com.github.cdtft.dao.core.SqlSessionFactory;
import com.github.cdtft.dao.core.SqlSessionFactoryBuilder;
import com.github.cdtft.daotest.pojo.User;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;

/**
 * @author : wangcheng
 * @date : 2020年10月01日 22:27
 */
public class QueryTest {

    public static void main(String[] args) {
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build("sqlConfig.xml");
            SqlSession sqlSession = sqlSessionFactory.openSqlSession();
            try {
                User user = new User();
                user.setId(1L);
                User findUser = sqlSession.findOne("user.findOne", user);
                System.out.println(findUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException | PropertyVetoException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
