package xyz.wenjiesx.demo.designpattern.observer;

/**
 * 观察者模式
 *
 * @author wenji
 * @date 2019/10/30
 */
public class ObserverTest {

    public static void main(String[] args) {
        MySubject mySubject = new MySubject();
        mySubject.add(new Observer1());
        mySubject.add(new Observer2());

        mySubject.operation();
    }

}
