package xyz.wenjiesx.demo.spi;

/**
 * @author wenji
 * @date 2019/11/2
 */
public class Bumblebee implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
