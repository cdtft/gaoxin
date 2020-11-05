package com.github.cdtft.dao.executor;

import com.github.cdtft.dao.core.CdtftSqlStatement;

import javax.sql.DataSource;
import java.util.List;

/**
 * sql执行接口
 *
 * @author : wangcheng
 * @date : 2020年10月01日 10:29
 */
public interface CdtftSqlExecutor {

    <T> List<T> query(DataSource dataSource, CdtftSqlStatement sqlStatement, Object... param) throws Exception;

    void close() throws Exception;
}
