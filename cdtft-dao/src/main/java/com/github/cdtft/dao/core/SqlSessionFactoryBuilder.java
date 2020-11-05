package com.github.cdtft.dao.core;

import com.github.cdtft.dao.io.Resource;
import com.github.cdtft.dao.xml.XMLConfigurerBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author : wangcheng
 * @date : 2020年09月29日 17:27
 */
public class SqlSessionFactoryBuilder {

    private final Configuration configuration;

    public SqlSessionFactoryBuilder() {
        this.configuration = new Configuration();
    }

    public SqlSessionFactory build(String path) throws FileNotFoundException, PropertyVetoException, DocumentException {
        InputStream inputStream = Resource.getResourcesInputStream(path);
        XMLConfigurerBuilder xmlConfigurerBuilder = new XMLConfigurerBuilder(configuration);
        xmlConfigurerBuilder.parse(inputStream);
        return new DefaultSqlSessionFactory(configuration);
    }
}