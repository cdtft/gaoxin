package com.github.cdtft.dao.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年10月01日 15:23
 */
public class ParameterMappingTokenHandler implements TokenHandler {

    private final List<String> parameterList = new ArrayList<>();

    @Override
    public String handleToken(String content) {
        parameterList.add(content);
        return "?";
    }

    public List<String> getParameterList() {
        return parameterList;
    }
}
