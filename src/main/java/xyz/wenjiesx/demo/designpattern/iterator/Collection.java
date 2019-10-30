package xyz.wenjiesx.demo.designpattern.iterator;

/**
 * @author wenji
 * @date 2019/10/30
 */
public interface Collection {

    /**
     * iterator
     */
    public Iterator iterator();

    /**
     * get element by index
     * @param i index
     * @return
     */
    public Object get(int i);

    /**
     * get collection size
     * @return
     */
    public int size();


}
