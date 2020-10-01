package com.github.cdtft.dao.core;

import com.github.cdtft.dao.io.Resource;

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

    public static SqlSessionFactory build(String path) throws FileNotFoundException {
        InputStream inputStream = Resource.getResourcesInputStream(path);

        return null;
    }
}