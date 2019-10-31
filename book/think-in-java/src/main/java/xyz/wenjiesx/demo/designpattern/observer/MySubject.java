package xyz.wenjiesx.demo.designpattern.observer;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class MySubject extends AbstractSubject {
    @Override
    public void operation() {
        System.out.println("update self");
        notifyObservers();
    }
}
