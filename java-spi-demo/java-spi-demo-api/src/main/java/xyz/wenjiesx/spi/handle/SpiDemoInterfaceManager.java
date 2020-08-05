package xyz.wenjiesx.spi.handle;

import xyz.wenjiesx.spi.api.SpiDemoInterface;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author wenji
 * @Date 2020/8/4
 */
public class SpiDemoInterfaceManager {

    public static SpiDemoInterface spiDemoInterface;

    static {
        ServiceLoader<SpiDemoInterface> serviceLoader = ServiceLoader.load(SpiDemoInterface.class);
        Iterator<SpiDemoInterface> iterator = serviceLoader.iterator();

        if (iterator.hasNext()) {
            spiDemoInterface = iterator.next();
        } else {
            throw new RuntimeException("没有找到任何SpiDemoInterface的实现类");
        }
    }

    public static SpiDemoInterface getInstance() {
        return spiDemoInterface;
    }

}
