package xyz.wenjiesx.demo.designpattern.adapter;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
