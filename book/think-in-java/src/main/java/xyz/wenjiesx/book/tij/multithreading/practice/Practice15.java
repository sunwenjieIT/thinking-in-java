package xyz.wenjiesx.book.tij.multithreading.practice;

/**
 * 练习15
 * 创建一个类, 具有三个方法
 * 这些方法包含一个临界区, 所有对该临界区的同步都是在同一个对象上的
 * 创建多个任务来演示这些方法同时只能运行一个
 * <p>
 * 现在修改这些方法, 使得每个方法都在不同的对象上同步
 * 并展示三个方法可以同时运行
 *
 * @author wenji
 * @date 2019/10/20
 */
public class Practice15 {

    public static void main(String[] args) {

        final SyncTest1 syncTest1 = new SyncTest1();

        new Thread() {
            @Override
            public void run() {
                syncTest1.methodA();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                syncTest1.methodB();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                syncTest1.methodC();
            }
        }.start();

        final SyncTest2 syncTest2 = new SyncTest2();
        new Thread() {
            @Override
            public void run() {
                syncTest2.methodA();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                syncTest2.methodB();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                syncTest2.methodC();
            }
        }.start();

    }
}

class SyncTest1 {

    public void methodA() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("this is method A");
                Thread.yield();
            }
        }
    }

    public void methodB() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {

                System.out.println("this is method B");
                Thread.yield();

            }
        }
    }

    public void methodC() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {

                System.out.println("this is method C");
                Thread.yield();
            }
        }
    }

}

class SyncTest2 {
    Object sync1 = new Object();
    Object sync2 = new Object();
    Object sync3 = new Object();
    public void methodA() {
        synchronized (sync1) {
            for (int i = 0; i < 5; i++) {
                System.out.println("this is test2 method A");
                Thread.yield();
            }
        }
    }

    public void methodB() {
        synchronized (sync2) {
            for (int i = 0; i < 5; i++) {

                System.out.println("this is test2 method B");
                Thread.yield();

            }
        }
    }

    public void methodC() {
        synchronized (sync3) {
            for (int i = 0; i < 5; i++) {

                System.out.println("this is test2 method C");
                Thread.yield();
            }
        }
    }
}