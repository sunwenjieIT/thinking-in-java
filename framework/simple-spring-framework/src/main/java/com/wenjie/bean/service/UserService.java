package com.wenjie.bean.service;

import com.wenjie.springframework.annotation.Component;
import com.wenjie.springframework.annotation.Transactional;

/**
 * @author wenji
 * @Date 2023/1/8
 */
@Component
public class UserService {



    public void test() {
        System.out.println("run user service method test");
    }

    @Transactional
    public void test2() {
        System.out.println("run user service method test2");
    }

}
