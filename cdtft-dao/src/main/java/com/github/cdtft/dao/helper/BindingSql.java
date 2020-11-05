package com.github.cdtft.dao.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年10月01日 15:17
 */
public class BindingSql {

    private String executeSql;

    private List<String> parameterList = new ArrayList<>();

    public BindingSql(String executeSql, List<String> parameterList) {
        this.executeSql = executeSql;
        this.parameterList = parameterList;
    }

    public String getExecuteSql() {
        return executeSql;
    }

    public void setExecuteSql(String executeSql) {
        this.executeSql = executeSql;
    }

    public List<String> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<String> parameterList) {
        this.parameterList = parameterList;
    }
}
