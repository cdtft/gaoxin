package com.github.cdtft.ioc.bean;

import java.util.StringJoiner;

/**
 * @author : wangcheng
 * @date : 2020年10月09日 14:29
 */
public class ProductService {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProductService.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }
}
