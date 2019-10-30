package xyz.wenjiesx.demo.designpattern.builder;

import xyz.wenjiesx.demo.designpattern.factorymethod.MailSender;
import xyz.wenjiesx.demo.designpattern.factorymethod.Sender;
import xyz.wenjiesx.demo.designpattern.factorymethod.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Builder {

    private List<Sender> list = new ArrayList<>();

    public void buildMailSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }

    public void buildSmsSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
    }

}
