package com.github.cdtft.dao.util;

/**
 * @author : wangcheng
 * @date : 2020年09月30日 23:41
 */
public class ClassLoadUtils {

    public static Class<?> load(String className) {
        Class<?> clazz = null;
        try {
            clazz = ClassLoader.getSystemClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
