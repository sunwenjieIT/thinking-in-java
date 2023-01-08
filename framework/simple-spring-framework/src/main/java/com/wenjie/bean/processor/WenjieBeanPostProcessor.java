package com.wenjie.bean.processor;

import com.wenjie.springframework.processor.BeanPostProcessor;

/**
 * @author wenji
 * @Date 2023/1/8
 */
public class WenjieBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println(String.format("do bean post processor before %s init, bean: %s", beanName, bean));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println(String.format("do bean post processor after %s init, bean: %s", beanName, bean));
        return bean;
    }
}
