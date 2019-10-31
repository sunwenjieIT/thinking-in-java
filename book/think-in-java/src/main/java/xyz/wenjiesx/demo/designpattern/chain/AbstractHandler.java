package xyz.wenjiesx.demo.designpattern.chain;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class AbstractHandler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
