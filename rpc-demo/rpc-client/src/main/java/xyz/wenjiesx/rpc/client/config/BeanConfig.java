package xyz.wenjiesx.rpc.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.wenjiesx.rpc.client.core.RpcClientFrame;
import xyz.wenjiesx.rpc.server.api.UserService;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@Configuration
public class BeanConfig {

    @Autowired
    private RpcClientFrame rpcClientFrame;

    @Bean
    public UserService getUserService() throws Exception {
        return rpcClientFrame.getRemoteProxyObject(UserService.class);
    }

}
