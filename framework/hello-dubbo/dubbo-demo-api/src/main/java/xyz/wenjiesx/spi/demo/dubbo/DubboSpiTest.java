package xyz.wenjiesx.spi.demo.dubbo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import xyz.wenjiesx.spi.demo.java.Robot;

/**
 * dubbo SPI的测试代码
 * 使用DUBBO SPI时和JAVA SPI不同, 需要在接口类上加@SPI注释. META-INF中的数据需要按K-V的格式来书写
 * @author wenji
 * @date 2019/11/2
 */
public class DubboSpiTest {
    public static void main(String[] args) {


        ExtensionLoader<Robot> extensionLoader =
                ExtensionLoader.getExtensionLoader(Robot.class);
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();

    }
}
