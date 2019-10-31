package xyz.wenjiesx.demo.designpattern.factorymethod;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class SmsSenderFactory implements SendFactoryInterface {

    @Override
    public Sender newSender() {
        return new SmsSender();
    }



}
