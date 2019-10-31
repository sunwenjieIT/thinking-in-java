package xyz.wenjiesx.demo.designpattern.memento;

/**
 * 备忘录模式示例
 * @author wenji
 * @date 2019/10/31
 */
public class MementoTest {

    public static void main(String[] args) {

        Original original = new Original("first");

        Storage storage = new Storage(original.createMemento());

        System.out.println("original: " + original.getValue());
        original.setValue("second");
        System.out.println("after update: " + original.getValue());

        original.restoreMemento(storage.getMemento());
        System.out.println("after restore: " + original.getValue());

    }

}
