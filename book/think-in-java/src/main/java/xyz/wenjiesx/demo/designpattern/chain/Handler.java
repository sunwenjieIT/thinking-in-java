package xyz.wenjiesx.demo.designpattern.chain;

/**
 * @author wenji
 * @date 2019/10/31
 */
public interface Handler {

    /**
     * 某个操作
     */
    public void operator();

    /**
     * 责任链设置
     * @param handler
     */
    public void setHandler(Handler handler);
}
