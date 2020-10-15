package com.github.cdtft.ioc.container;

import com.github.cdtft.ioc.reader.XmlReader;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : wangcheng
 * @date : 2020年10月09日 09:43
 */
public class DefaultBeanContainer implements BeanContainer {

    public final ConcurrentHashMap<String, Object> container = new ConcurrentHashMap<>(255);

    public DefaultBeanContainer() {
        new XmlReader().loadXml(this);
    }

    @Override
    public <T> T getBeanForName(String name) {
        return (T) container.get(name);
    }

    @Override
    public void registerBean(String name, Object bean) {
        container.put(name, bean);
    }
}
