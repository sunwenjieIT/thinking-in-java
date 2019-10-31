package xyz.wenjiesx.demo.designpattern.chain;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class MyHandler extends AbstractHandler implements Handler {

    private String name;

    public MyHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println("do sth:" + name);
        if (getHandler() != null) {
            getHandler().operator();
        }
    }

}
