package xyz.wenjiesx.framework.dubbo.demo;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wenji
 * @date 2019/11/1
 */
@EnableAutoConfiguration
public class DubboDemoConsumerApplication {

    @Reference(url = "dubbo://127.0.0.1:20880")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoConsumerApplication.class, args).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            System.out.println(demoService.sayHello("message from consumer"));

        };
    }

}
