package com.github.cdtft.ioc.container;

/**
 * @author : wangcheng
 * @date : 2020年10月09日 09:41
 */
public interface BeanContainer {

    /**
     * 通过名称查找bean
     *
     * @param name
     * @param <T>
     * @return
     */
    <T> T getBeanForName(String name);

    /**
     * bean注册
     *
     * @param name
     * @param bean
     */
    void registerBean(String name, Object bean);

}
