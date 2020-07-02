package xyz.wenjiesx.book.tij.multithreading.practice;

import xyz.wenjiesx.book.tij.multithreading.LiftOff;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * 练习28
 * 修改TestBlockingQueue.java 添加一个将LiftOff防止到BlockingQueue中的任务
 * 而不要防止在main中
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice28 {

    static void getKey() {
        try {
            // Compensate for Windows/Linux difference in the
            // length of the result produced by the Enter key:
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch(java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void getKey(String message) {
        println(message);
        getKey();
    }
    static void test(String msg, BlockingQueue<LiftOff> queue) {
        println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        LiftOffAdder adder = new LiftOffAdder(runner);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(runner);
        exec.execute(adder);
        getKey("Press 'Enter' (" + msg + ")");
        println("Finished " + msg + " test");
        exec.shutdownNow();
    }
    public static void main(String[] args) {
        test("LinkedBlockingQueue", // Unlimited size
                new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue", // Fixed size
                new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", // Size of 1
                new SynchronousQueue<LiftOff>());
    }

}

class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;
    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }
    public void add(LiftOff lo) {
        try {
            rockets.put(lo);
        } catch(InterruptedException e) {
            println("Interrupted during put()");
        }
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch(InterruptedException e) {
            println("Waking from take()");
        }
        println("Exiting LiftOffRunner");
    }
}

class LiftOffAdder implements Runnable {
    private LiftOffRunner runner;
    public LiftOffAdder(LiftOffRunner runner) {
        this.runner = runner;
    }
    public void run() {
        for(int i = 0; i < 5; i++)
            runner.add(new LiftOff(5));
    }
}
