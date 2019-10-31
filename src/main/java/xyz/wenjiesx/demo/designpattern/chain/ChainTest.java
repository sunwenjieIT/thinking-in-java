package xyz.wenjiesx.demo.designpattern.chain;

/**
 * 责任链模式示例
 * @author wenji
 * @date 2019/10/31
 */
public class ChainTest {

    public static void main(String[] args) {
        Handler handler1 = new MyHandler("handler1");
        Handler handler2 = new MyHandler("handler2");
        Handler handler3 = new MyHandler("handler3");

        handler1.setHandler(handler2);
        handler2.setHandler(handler3);

        handler1.operator();

    }

}
