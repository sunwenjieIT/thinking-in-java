package xyz.wenjiesx.framework.dubbo.demo.provider;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import xyz.wenjiesx.framework.dubbo.demo.DemoService;

/**
 * @author wenji
 * @date 2019/11/1
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
