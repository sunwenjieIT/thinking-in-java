package xyz.wenjiesx.demo.designpattern.visitor;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class MySubject implements Subject {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}
