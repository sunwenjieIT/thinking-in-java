package xyz.wenjiesx.rpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wenjiesx.rpc.server.api.UserService;
import xyz.wenjiesx.rpc.server.dto.UserInfo;

import javax.annotation.Resource;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@RestController
@RequestMapping("/user")
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserInfo getUser(@PathVariable Long userId) {
        System.out.println("======get user by id " + userId + "====");
        return userService.getUser(userId);
    }

}
