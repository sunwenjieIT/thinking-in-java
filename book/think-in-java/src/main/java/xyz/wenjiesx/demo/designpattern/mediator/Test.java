package xyz.wenjiesx.demo.designpattern.mediator;

/**
 * 中介模式示例
 * @author wenji
 * @date 2019/10/31
 */
public class Test {
    public static void main(String[] args) {
        Mediator mediator = new MyMediator();
        mediator.createMediator();
        mediator.workAll();

    }
}
