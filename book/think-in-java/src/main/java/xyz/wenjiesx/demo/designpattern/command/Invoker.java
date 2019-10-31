package xyz.wenjiesx.demo.designpattern.command;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action() {
        command.exec();
    }
}
