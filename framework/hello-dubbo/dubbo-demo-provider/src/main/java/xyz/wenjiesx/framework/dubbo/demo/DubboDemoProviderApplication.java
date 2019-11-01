package xyz.wenjiesx.framework.dubbo.demo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author wenji
 * @date 2019/11/1
 */
//@EnableDubbo
@EnableAutoConfiguration
public class DubboDemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoProviderApplication.class, args);
    }

}
