package xyz.wenjiesx.demo.designpattern.visitor;

/**
 * 访问者模式
 * @author wenji
 * @date 2019/10/31
 */
public class Test {
    public static void main(String[] args) {
        Visitor visitor = new MyVisitor();
        Subject subject = new MySubject();

        subject.accept(visitor);
    }
}
