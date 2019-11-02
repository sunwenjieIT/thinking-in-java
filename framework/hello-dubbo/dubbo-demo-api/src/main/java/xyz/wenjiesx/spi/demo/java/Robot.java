package xyz.wenjiesx.spi.demo.java;

import org.apache.dubbo.common.extension.SPI;

/**
 *
 * @author wenji
 * @date 2019/11/2
 */
@SPI
public interface Robot {
    /**
     * java spi示例的接口代码
     */
    void sayHello();
}
