package xyz.wenjiesx.demo.designpattern.factorymethod;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is sms sender");
    }
}
