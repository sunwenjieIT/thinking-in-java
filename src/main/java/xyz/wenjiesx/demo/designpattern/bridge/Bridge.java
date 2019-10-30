package xyz.wenjiesx.demo.designpattern.bridge;

import xyz.wenjiesx.demo.designpattern.decorator.SourceAble;

/**
 * @author wenji
 * @date 2019/10/30
 */
public abstract class Bridge {

    private SourceAble source;

    public void method() {
        source.method();

    }

    public SourceAble getSource() {
        return source;
    }

    public void setSource(SourceAble source) {
        this.source = source;
    }


}
