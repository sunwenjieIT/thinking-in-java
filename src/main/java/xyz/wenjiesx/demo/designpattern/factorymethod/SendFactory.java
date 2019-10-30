package xyz.wenjiesx.demo.designpattern.factorymethod;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class SendFactory {

    public Sender newSender(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("type error");
            return null;
        }
    }

    public static Sender newMailSender() {
        return new MailSender();
    }

    public static Sender newSmsSender() {
        return new SmsSender();
    }

}
