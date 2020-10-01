package com.github.cdtft.dao.xml;

import com.github.cdtft.dao.core.Configuration;
import com.github.cdtft.dao.io.Resource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author : wangcheng
 * @date : 2020年09月29日 17:42
 */
public class XMLConfigurerBuilder {

    private final Configuration configuration;

    public XMLConfigurerBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration parse(InputStream inputStream) throws DocumentException, PropertyVetoException, FileNotFoundException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> dataSourceElement = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element e : dataSourceElement) {
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            properties.setProperty(name, value);
        }
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(properties.getProperty("url"));
        dataSource.setDriverClass(properties.getProperty("driverClass"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(dataSource);

        List<Element> sqlConfigElements = rootElement.selectNodes("//sqls");
        for (Element sqlConfig : sqlConfigElements) {
            String resource = sqlConfig.attributeValue("resource");
            XMLSqlBuilder xmlSqlBuilder = new XMLSqlBuilder(configuration);
            xmlSqlBuilder.parse(Resource.getResourcesInputStream(resource));
        }
        return configuration;
    }

}
