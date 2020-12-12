package xyz.wenjiesx.rpc.register.core;

import xyz.wenjiesx.rpc.core.register.RegisterService;

/**
 * @author wenji
 * @Date 2020/12/12
 */
//@Configuration
//@ConditionalOnClass(RegisterService.class)
//@EnableConfigurationProperties
public class RegisterAutoConfigure {

//    @Bean
//    @ConditionalOnMissingBean
    RegisterService startService() {
        return new RegisterServiceServerImpl();
    }

}