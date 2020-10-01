package com.github.cdtft.dao.core;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * hold dataSource and all sql statement
 *
 * @author : wangcheng
 * @date : 2020年09月29日 10:56
 */
public class Configuration {

    private DataSource dataSource;

    private Map<String, CdtftSqlStatement> sqlStatementMap = new HashMap<>(64);

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, CdtftSqlStatement> getSqlStatementMap() {
        return sqlStatementMap;
    }

    public void setSqlStatementMap(Map<String, CdtftSqlStatement> sqlStatementMap) {
        this.sqlStatementMap = sqlStatementMap;
    }
}
