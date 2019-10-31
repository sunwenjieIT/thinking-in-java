package xyz.wenjiesx.demo.designpattern.command;

/**
 *
 * @author wenji
 * @date 2019/10/31
 */
public class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exec() {
        receiver.action();
    }
}
