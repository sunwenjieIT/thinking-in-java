package xyz.wenjiesx.demo.designpattern.decorator;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Decorator implements SourceAble {

    private SourceAble source;

    public Decorator(SourceAble source) {
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("before");
        source.method();
        System.out.println("after");
    }
}
