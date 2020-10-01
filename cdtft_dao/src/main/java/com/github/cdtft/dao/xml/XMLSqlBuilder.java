package com.github.cdtft.dao.xml;

import com.github.cdtft.dao.core.CdtftSqlStatement;
import com.github.cdtft.dao.core.Configuration;
import com.github.cdtft.dao.util.ClassLoadUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年09月29日 22:28
 */
public class XMLSqlBuilder {

    private final static String SEPARATOR = ".";

    private final Configuration configuration;

    public XMLSqlBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * xml convert to sqlstatment
     *
     * @param inputStream
     * @return
     * @throws DocumentException
     */
    public Configuration parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectNodes = rootElement.selectNodes("//select");
        for (Element element : selectNodes) {
            CdtftSqlStatement cdtftSqlStatement = new CdtftSqlStatement();
            String id = element.attributeValue("id");
            String parameterType = element.attributeValue("parameterType");
            String resultType = element.attributeValue("resultType");
            cdtftSqlStatement.setId(id);
            cdtftSqlStatement.setParameterType(ClassLoadUtils.load(parameterType));
            cdtftSqlStatement.setResultType(ClassLoadUtils.load(resultType));
            cdtftSqlStatement.setSql(element.getTextTrim());
            configuration.getSqlStatementMap().put(namespace + SEPARATOR + id, cdtftSqlStatement);
        }
        return configuration;
    }
}
