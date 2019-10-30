package xyz.wenjiesx.demo.designpattern.iterator;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class MyCollection implements Collection {

    public String[] string = {"A", "B", "C", "D"};

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return string[i];
    }

    @Override
    public int size() {
        return string.length;
    }
}
