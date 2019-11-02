package xyz.wenjiesx.spi.demo.java;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * java SPI 示例代码
 * @author wenji
 * @date 2019/11/2
 */
public class JavaSPITest {
    public static void main(String[] args) {

        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        Iterator<Robot> iterator = serviceLoader.iterator();

        while (iterator.hasNext()) {
            Robot next = iterator.next();
            next.sayHello();
        }

    }
}
