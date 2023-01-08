package com.wenjie.bean.service;

import com.wenjie.springframework.annotation.Autowired;
import com.wenjie.springframework.annotation.Component;
import com.wenjie.springframework.aware.ApplicationContextAware;
import com.wenjie.springframework.aware.BeanNameAware;
import com.wenjie.springframework.core.WenjieApplicationContext;

/**
 * @author wenji
 * @Date 2023/1/8
 */
@Component
public class OrderService implements ApplicationContextAware, BeanNameAware {

    private WenjieApplicationContext context;
    private String beanName;

    @Autowired
    private UserService userService;

    public void test() {
        System.out.println("run order service method test");
        userService.test();
    }

    public void test2() {
        System.out.println("run order service method test2");
        userService.test2();
    }

    public void test3() {
        System.out.println(context);
        System.out.println(beanName);
    }

    @Override
    public void setApplicationContext(WenjieApplicationContext context) {
        this.context = context;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
