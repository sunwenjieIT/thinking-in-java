package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 练习13
 * 使用synchronized来修复
 * SerialNumberChecker.java
 * 该类验证了++的非原子性. 在多个线程内不断累加公共变量的过程中检查缓存数组里是否已存在新累加值.
 * 检查和添加缓存的操作已加锁.概率得到++后值已存在缓存中的问题. 证明++非原子性.
 * 证明安全
 *
 * @author wenji
 * @date 2019/10/19
 */
public class Practice13SerialNumberCheckerRepair {


    private static final int SIZE = 10;
    private static CircularSet serials =
            new CircularSet(1000);
    private static ExecutorService exec =
            Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        public void run() {
            while (true) {
                int serial =
                        SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);

                }
                serials.add(serial);

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new SerialChecker());

            if (args.length > 0) {
                TimeUnit.SECONDS.sleep(new Integer(args[0]));
                System.out.println("No duplicates detected");
                System.exit(0);

            }
        }
    }


}

class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        this.len = size;
        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++) {
            if(array[i] == val) {
                return true;
            }
        }
        return false;
    }
}

class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    /**
     * ++不属于原子操作, 有读写两步. 如果要预防可视性问题, 需要添加锁保持原子性
     * @return
     */
    public static synchronized int nextSerialNumber() {
        return serialNumber++;
    }
}