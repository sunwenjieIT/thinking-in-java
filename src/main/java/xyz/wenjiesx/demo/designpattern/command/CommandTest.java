package xyz.wenjiesx.demo.designpattern.command;

/**
 * 命令模式示例
 * @author wenji
 * @date 2019/10/31
 */
public class CommandTest {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new MyCommand(receiver);
        Invoker invoker = new Invoker(command);

        invoker.action();
    }
}
