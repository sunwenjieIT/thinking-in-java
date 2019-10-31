package xyz.wenjiesx.demo.designpattern.observer;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Observer1 implements Observer {
    @Override
    public void update() {
        System.out.println("observer1 has received!");

    }
}