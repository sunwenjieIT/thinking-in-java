package xyz.wenjiesx.demo.designpattern.bridge;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class MyBridge extends Bridge {

    @Override
    public void method() {
        getSource().method();
    }
}
