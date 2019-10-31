package xyz.wenjiesx.demo.designpattern.bridge;

import xyz.wenjiesx.demo.designpattern.decorator.SourceAble;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class SourceSub1 implements SourceAble {
    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}
