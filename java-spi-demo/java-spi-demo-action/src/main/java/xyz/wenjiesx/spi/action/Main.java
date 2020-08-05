package xyz.wenjiesx.spi.action;

import xyz.wenjiesx.spi.api.SpiDemoInterface;
import xyz.wenjiesx.spi.handle.SpiDemoInterfaceManager;

/**
 * @author wenji
 * @Date 2020/8/4
 */
public class Main {

    public static void main(String[] args) {
        SpiDemoInterface instance = SpiDemoInterfaceManager.getInstance();
        instance.helloSpi();
    }
}
