package com.github.cdtft.ioc.container;


import com.github.cdtft.ioc.bean.OrderService;
import org.junit.Test;

public class DefaultBeanContainerTest {

    @Test
    public void getBeanForName() {
        DefaultBeanContainer defaultBeanContainer = new DefaultBeanContainer();
        OrderService orderService = defaultBeanContainer.getBeanForName("orderService");
        System.out.println(orderService);
    }
}