package xyz.wenjiesx.demo.designpattern.builder;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class BuilderTest {

    public static void main(String[] args) {

        Builder builder = new Builder();
        builder.buildMailSender(20);

    }
}
