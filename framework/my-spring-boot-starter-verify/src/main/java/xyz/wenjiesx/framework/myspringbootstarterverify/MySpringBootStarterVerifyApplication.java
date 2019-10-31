package xyz.wenjiesx.framework.myspringbootstarterverify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wenjiesx.framework.myspringbootstarter.StarterService;

/**
 * @author wenji
 * @date 2019/10/31
 */
@SpringBootApplication
@RestController
public class MySpringBootStarterVerifyApplication {

    @Autowired
    private StarterService service;

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootStarterVerifyApplication.class, args);
    }

    @GetMapping("do")
    public String doService() {

        String[] split = service.split(",");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println("success");
        return "success";
    }


}
