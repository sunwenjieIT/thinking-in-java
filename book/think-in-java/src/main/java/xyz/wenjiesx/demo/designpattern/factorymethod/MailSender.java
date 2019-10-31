package xyz.wenjiesx.demo.designpattern.factorymethod;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is mail sender");
    }
}
