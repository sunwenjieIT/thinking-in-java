package xyz.wenjiesx.book.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习11
 * 创建一个类
 * 它包含两个数据域和一个操作这些域的方法
 * 操作过程是多步骤的,这样在该方法执行过程中, 这些域将处于"不正确的状态"
 * 添加读取这些域的方法, 创建多个线程去调用各种方法
 * 并展示处于"不正确状态的"数据是可视的.
 * 使用synchronized关键字修复这个问题
 *
 * @author wenji
 * @date 2019/10/19
 */
public class Practice11Synchronized implements Runnable {

    private MultipleOfThreeGenerator generator;

    private int tid;

    public Practice11Synchronized(MultipleOfThreeGenerator generator, int id) {
        this.generator = generator;
        this.tid = id;
    }

    public void run() {

        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 3 != 0) {
                System.out.println("tid " + tid + " int " + val + " is not multiple of 3");
                generator.cancel();
            }
        }
    }

    public static void main(String[] args) {
        MultipleOfThreeGenerator generator = new MultipleOfThreeGenerator();
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            exec.execute(new Practice11Synchronized(generator, i));
        }
        exec.shutdown();

    }

}

class MultipleOfThreeGenerator {

    private volatile boolean canceled = false;
    private int currentNum = 0;
    public synchronized int next() {
        currentNum++;
        currentNum++;
        currentNum++;
        return currentNum;
    }

    public boolean isCanceled() {
        return canceled;
    }
    public void cancel() {
        this.canceled = true;
    }

}
