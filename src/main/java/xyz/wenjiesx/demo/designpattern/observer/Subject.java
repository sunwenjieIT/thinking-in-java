package xyz.wenjiesx.demo.designpattern.observer;

/**
 * @author wenji
 * @date 2019/10/30
 */
public interface Subject {

    /**
     * add observer
     * @param observer
     */
    public void add(Observer observer);

    /**
     * del observer
     * @param observer
     */
    public void del(Observer observer);

    /**
     * notify all observer
     */
    public void notifyObservers();

    /**
     * do sth
     */
    public void operation();



}
