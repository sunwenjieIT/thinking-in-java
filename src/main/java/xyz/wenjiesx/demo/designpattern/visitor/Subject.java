package xyz.wenjiesx.demo.designpattern.visitor;

/**
 * @author wenji
 * @date 2019/10/31
 */
public interface Subject {

    public void accept(Visitor visitor);

    public String getSubject();

}
