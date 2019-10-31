package xyz.wenjiesx.demo.designpattern.iterator;

/**
 * @author wenji
 * @date 2019/10/30
 */
public interface Iterator {

    /**
     * get previous
     * @return
     */
    public Object previous();

    /**
     * get next
     * @return
     */
    public Object next();

    /**
     * check is has next
     * @return
     */
    public boolean hasNext();

    /**
     * get first
     * @return
     */
    public Object first();
}
