package xyz.wenjiesx.demo.designpattern.factorymethod;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        // 一般工厂模式
        SendFactory sendFactory = new SendFactory();
        Sender sms = sendFactory.newSender("sms");
        Sender mail = sendFactory.newSender("mail");
        sms.send();
        mail.send();

        Sender other = sendFactory.newSender("other");

        // 静态方法 工厂模式
        Sender mail2 = SendFactory.newMailSender();
        Sender sms2 = SendFactory.newSmsSender();
        sms2.send();
        mail2.send();

        // 抽象工厂模式
        Sender mailSender = new MailSenderFactory().newSender();
        Sender smsSender = new SmsSenderFactory().newSender();
        mailSender.send();
        smsSender.send();

    }

}
