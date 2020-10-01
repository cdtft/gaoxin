package com.github.cdtft.dao.core;

import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年10月01日 10:06
 */
public interface SqlSession {

    public <E> List<E> findAll(String statementId, Object... param) throws Exception;

    public <T> T findOne(String statementId, Object... param) throws Exception;

    public void close() throws Exception;

}
