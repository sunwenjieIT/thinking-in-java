package xyz.wenjiesx.demo.designpattern.decorator;

/**
 * 装饰器模式示例
 *
 * @author wenji
 * @date 2019/10/30
 */
public class DecoratorTest {
    public static void main(String[] args) {
        SourceAble source = new Source();
        SourceAble decorator = new Decorator(source);

        decorator.method();

    }
}
