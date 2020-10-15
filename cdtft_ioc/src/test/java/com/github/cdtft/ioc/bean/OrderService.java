package com.github.cdtft.ioc.bean;

import java.util.StringJoiner;

/**
 * @author : wangcheng
 * @date : 2020年10月09日 14:17
 */
public class OrderService {

    private ProductService productService;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderService.class.getSimpleName() + "[", "]")
                .add("productService=" + productService)
                .toString();
    }
}
