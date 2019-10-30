package xyz.wenjiesx.demo.designpattern.adapter;

/**
 * 对象适配器demo
 * @author wenji
 * @date 2019/10/30
 */
public class Wrapper implements Targetable{

    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetAble method");
    }
}
