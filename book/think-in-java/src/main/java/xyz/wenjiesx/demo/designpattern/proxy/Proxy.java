package xyz.wenjiesx.demo.designpattern.proxy;

import xyz.wenjiesx.demo.designpattern.decorator.Source;
import xyz.wenjiesx.demo.designpattern.decorator.SourceAble;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Proxy implements SourceAble {

    private Source source;

    public Proxy() {
        this.source = new Source();
    }

    @Override
    public void method() {
        before();
        source.method();
        after();

    }

    public void before() {
        System.out.println("before proxy");
    }

    public void after() {
        System.out.println("after proxy");
    }
}
