package xyz.wenjiesx.demo.designpattern.decorator;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Source implements SourceAble {
    @Override
    public void method() {
        System.out.println("this is original method");

    }
}
