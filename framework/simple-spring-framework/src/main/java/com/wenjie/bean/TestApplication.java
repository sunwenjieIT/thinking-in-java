package com.wenjie.bean;

import com.wenjie.bean.service.OrderService;
import com.wenjie.springframework.annotation.ComponentScan;
import com.wenjie.springframework.core.WenjieApplicationContext;

/**
 * @author wenji
 * @Date 2023/1/8
 */
@ComponentScan("com.wenjie.bean")
public class TestApplication {

    public static void main(String[] args) {
        WenjieApplicationContext applicationContext = new WenjieApplicationContext(TestApplication.class);
        System.out.println(applicationContext);

        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        orderService.test();
        orderService.test2();
        orderService.test3();
    }
}
