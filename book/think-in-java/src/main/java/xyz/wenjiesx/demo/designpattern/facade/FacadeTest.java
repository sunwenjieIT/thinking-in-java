package xyz.wenjiesx.demo.designpattern.facade;

/**
 * facade 外观模式示例
 *
 * @author wenji
 * @date 2019/10/30
 */
public class FacadeTest {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }

}
