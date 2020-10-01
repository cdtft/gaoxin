package com.github.cdtft.dao.core;

/**
 * sql配置文件将解析成该对象
 *
 * @author : wangcheng
 * @date : 2020年09月29日 10:57
 */
public class CdtftSqlStatement {

    private String id;

    private String sql;

    private Class<?> parameterType;

    private Class<?> resultType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }
}
