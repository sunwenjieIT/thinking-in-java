package xyz.wenjiesx.spi.impl;

import xyz.wenjiesx.spi.api.SpiDemoInterface;

/**
 * @author wenji
 * @Date 2020/8/4
 */
public class SpiDemoImplA implements SpiDemoInterface {
    @Override
    public void helloSpi() {
        System.out.println("Im impl1");
    }
}
