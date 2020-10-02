package com.github.cdtft.dao.core;

import com.github.cdtft.dao.executor.CdtftSqlExecutor;
import com.github.cdtft.dao.executor.SimpleCdtftSqlExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
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
    public <M> M getMapper(Class<?> mapperClass) {
        M proxyMapper = (M) Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass}, (proxy, method, args) -> {
            String methodName = method.getName();
            Class<?> declaringClass = method.getDeclaringClass();
            String className = declaringClass.getName();
            String[] strings = className.split("\\.");
            String namespace = strings[strings.length-1].replace("Mapper", "").toLowerCase();
            boolean contains = configuration.getNamespaces().contains(namespace);
            if (!contains) {
                throw new RuntimeException("未找到" + className + "操作的实体对象" + namespace);
            }
            String statementId = namespace + "." + methodName;
            Type genericReturnType = method.getGenericReturnType();
            //判断是否范型化，若是范型就返回List
            if (genericReturnType instanceof ParameterizedType) {
                return findAll(statementId, args);
            }
            return findOne(statementId, args);
        });
        return proxyMapper;
    }

    @Override
    public void close() throws Exception {
        executor.close();
    }
}
