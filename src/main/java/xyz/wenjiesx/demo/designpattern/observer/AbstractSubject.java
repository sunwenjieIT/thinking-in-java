package xyz.wenjiesx.demo.designpattern.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author wenji
 * @date 2019/10/30
 */
public abstract class AbstractSubject implements Subject{

    private Vector<Observer> vector = new Vector<>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> elements = vector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().update();
        }
    }

}
