package xyz.wenjiesx.demo.designpattern.memento;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class Memento {

    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
