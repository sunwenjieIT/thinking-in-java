package xyz.wenjiesx.rpc.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import xyz.wenjiesx.rpc.core.register.RegisterService;

/**
 * @author wenji
 * @date 2019/10/25
 */
@Order(1)
@Configuration
@ConditionalOnClass(RegisterService.class)
@EnableConfigurationProperties(RegisterStarterServiceProperties.class)
public class RegisterClientStarterAutoConfigure {

    @Autowired
    private RegisterStarterServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "register.service.center", value = "enabled", havingValue = "true")
    RegisterService starterService (){
        System.out.println("========register service client init========");
        return new RegisterServiceClientImpl(properties);
    }

}
