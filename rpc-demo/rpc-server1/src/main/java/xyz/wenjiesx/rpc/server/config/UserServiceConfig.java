package xyz.wenjiesx.rpc.server2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import xyz.wenjiesx.rpc.server.api.UserService;
import xyz.wenjiesx.rpc.server2.core.RpcServerFrame;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@Configuration
public class UserServiceConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private RpcServerFrame rpcServerFrame;

    @PostConstruct
    public void server() throws IOException {
        rpcServerFrame.startService(UserService.class.getName(),
                "127.0.0.1", 9999, UserService.class, userService);
    }

}
