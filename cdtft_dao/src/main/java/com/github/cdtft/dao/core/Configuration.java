package com.github.cdtft.dao.core;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * hold dataSource and all sql statement
 *
 * @author : wangcheng
 * @date : 2020年09月29日 10:56
 */
public class Configuration {

    private DataSource dataSource;

    private Map<String, CdtftSqlStatement> sqlStatementMap = new HashMap<>(64);

    private Set<String> namespaces = new HashSet<>();

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

    public Set<String> getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(Set<String> namespaces) {
        this.namespaces = namespaces;
    }
}
