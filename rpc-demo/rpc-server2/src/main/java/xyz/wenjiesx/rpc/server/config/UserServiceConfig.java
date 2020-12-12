package xyz.wenjiesx.rpc.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import xyz.wenjiesx.rpc.core.server.RpcServerFrame;
import xyz.wenjiesx.rpc.server.api.UserService;

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
                "127.0.0.1", 10001, userService);
    }

}
