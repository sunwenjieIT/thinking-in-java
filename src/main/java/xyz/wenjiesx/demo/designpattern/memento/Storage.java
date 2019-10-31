package xyz.wenjiesx.demo.designpattern.memento;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class Storage {

    private Memento memento;

    public Storage(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }


}
