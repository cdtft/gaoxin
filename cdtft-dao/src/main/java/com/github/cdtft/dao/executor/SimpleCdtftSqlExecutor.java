package com.github.cdtft.dao.executor;

import com.github.cdtft.dao.core.CdtftSqlStatement;
import com.github.cdtft.dao.helper.BindingSql;
import com.github.cdtft.dao.helper.GenericTokenParser;
import com.github.cdtft.dao.helper.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年10月01日 10:37
 */
public class SimpleCdtftSqlExecutor implements CdtftSqlExecutor {

    private Connection connection;

    @Override
    public <T> List<T> query(DataSource dataSource, CdtftSqlStatement sqlStatement, Object... param) throws Exception {
        if (connection == null) {
            connection = dataSource.getConnection();
        }
        BindingSql bindingSql = getBindingSql(sqlStatement.getSql());
        //需要执行的sql，将配置文件中SQL占位符替换为了？
        String executeSql = bindingSql.getExecuteSql();
        Class<?> parameterType = sqlStatement.getParameterType();
        PreparedStatement preparedStatement = connection.prepareStatement(executeSql);
        for (int i = 0; i < bindingSql.getParameterList().size(); i++) {
            String parameterFiledName = bindingSql.getParameterList().get(i);
            Field declaredField = parameterType.getDeclaredField(parameterFiledName);
            declaredField.setAccessible(true);
            //获取传入参数
            Object o = declaredField.get(param[0]);
            preparedStatement.setObject(i + 1, o);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        Class<?> resultClass = sqlStatement.getResultType();
        List<T> resultList = new ArrayList<>();
        while (resultSet.next()) {
            //每一行对应一个实例对象
            T t = (T) resultClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            //遍历每一列
            for (int i = 1; i < columnCount + 1; i++) {
                String columnName = metaData.getColumnName(i);
                Field declaredField = resultClass.getDeclaredField(columnName);
                Object columnValue = resultSet.getObject(columnName);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(t, declaredField.getType().cast(columnValue));
//                declaredField.setAccessible(true);
//                declaredField.set(t, columnValue);
            }
            resultList.add(t);
        }
        return resultList;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private BindingSql getBindingSql(String sql) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String parse = parser.parse(sql);
        return new BindingSql(parse, handler.getParameterList());
    }
}
