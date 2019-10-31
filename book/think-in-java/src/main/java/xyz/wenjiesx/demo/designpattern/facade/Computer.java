package xyz.wenjiesx.demo.designpattern.facade;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Computer {

    private Cpu cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        cpu = new Cpu();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup() {
        System.out.println("start computer begin");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("start computer finished");

    }

    public void shutdown() {
        System.out.println("start shutdown computer!");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("finish shutdown computer!");
    }
}
