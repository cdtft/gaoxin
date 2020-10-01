package com.github.cdtft.dao.core;

import com.github.cdtft.dao.executor.CdtftSqlExecutor;
import com.github.cdtft.dao.executor.SimpleCdtftSqlExecutor;

import java.sql.Connection;
import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年10月01日 10:07
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private final CdtftSqlExecutor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleCdtftSqlExecutor();
    }

    @Override
    public <E> List<E> findAll(String statementId, Object... param) throws Exception {
        return executor.query(configuration.getDataSource(), configuration.getSqlStatementMap().get(statementId), param);
    }

    @Override
    public <T> T findOne(String statementId, Object... param) throws Exception {
        List<T> t = findAll(statementId, param);
        if (t == null) {
            return null;
        }
        if (t.size() > 1) {
            throw new RuntimeException("结果不唯一");
        }
        return t.get(0);
    }

    @Override
    public void close() throws Exception {
        executor.close();
    }
}
