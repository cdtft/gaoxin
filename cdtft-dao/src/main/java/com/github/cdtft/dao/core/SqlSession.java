package com.github.cdtft.dao.core;

import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年10月01日 10:06
 */
public interface SqlSession {

    <E> List<E> findAll(String statementId, Object... param) throws Exception;

    <T> T findOne(String statementId, Object... param) throws Exception;

    /**
     * 生成动态代理对象
     *
     * @param mapperClass
     * @param <M>
     * @return
     */
    <M> M getMapper(Class<?> mapperClass);

    void close() throws Exception;

}
