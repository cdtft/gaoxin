package com.github.cdtft.dao.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 加载cdtftSql配置文件
 *
 * @author : wangcheng
 * @date : 2020年09月29日 10:50
 */
public class Resource {

    public static InputStream getResourcesInputStream(String path) throws FileNotFoundException {
        return Resource.class.getClassLoader().getResourceAsStream(path);
    }
}
