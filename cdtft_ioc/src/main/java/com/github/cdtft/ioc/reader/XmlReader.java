package com.github.cdtft.ioc.reader;

import com.github.cdtft.ioc.container.BeanContainer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年10月09日 14:07
 */
public class XmlReader {

    public void loadXml(BeanContainer container) {
        InputStream inputStream = XmlReader.class.getClassLoader().getResourceAsStream("bean.xml");
        SAXReader saxReader = new SAXReader();
        try {
            //读取xml，并实例化bean放入容器
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> beans = rootElement.selectNodes("//bean");
            for (Element bean : beans) {
                String id = bean.attributeValue("id");
                String clazz = bean.attributeValue("class");
                Class<?> instanceClass = Class.forName(clazz);
                Object instanceObj = instanceClass.newInstance();
                container.registerBean(id, instanceObj);
            }
            //注入依赖
            List<Element> propertiesElements = rootElement.selectNodes("//property");
            for (Element property : propertiesElements) {
                Element parentElement = property.getParent();
                String parentBeanId = parentElement.attributeValue("id");
                Object parentObject = container.getBeanForName(parentBeanId);
                String propertyName = property.attributeValue("name");
                String refBeanId = property.attributeValue("ref");
                Method[] methods = parentObject.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().replace("set", "").toLowerCase().equals(propertyName.toLowerCase())) {
                        method.invoke(parentObject, method.getParameters()[0].getType().cast(container.getBeanForName(refBeanId)));
                    }
                }
            }
        } catch (DocumentException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
