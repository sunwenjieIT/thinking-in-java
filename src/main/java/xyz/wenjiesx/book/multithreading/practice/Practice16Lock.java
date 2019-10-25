package xyz.wenjiesx.book.multithreading.practice;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 练习16
 * 使用显示的lock对线来修改练习15
 *
 * @author wenji
 * @date 2019/10/20
 */
public class Practice16Lock {

    public static void main(String[] args) {
        final LockTest1 lockTest1 = new LockTest1();

        new Thread() {
            @Override
            public void run() {
                lockTest1.methodA();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                lockTest1.methodB();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                lockTest1.methodC();
            }
        }.start();

        final LockTest2 lockTest2 = new LockTest2();
        new Thread() {
            @Override
            public void run() {
                lockTest2.methodA();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                lockTest2.methodB();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                lockTest2.methodC();
            }
        }.start();

    }
}

class LockTest1 {
    Lock lock = new ReentrantLock();

    public void methodA() {
        lock.lock();
        try {

            for (int i = 0; i < 5; i++) {
                System.out.println("this is method A");
                Thread.yield();
            }
        } finally {

            lock.unlock();
        }
    }

    public void methodB() {
        lock.lock();
        try {

            for (int i = 0; i < 5; i++) {
                System.out.println("this is method B");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }


    public void methodC() {
        lock.lock();
        try {

            for (int i = 0; i < 5; i++) {
                System.out.println("this is method C");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }
}


class LockTest2 {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();
    Lock lock3 = new ReentrantLock();

    public void methodA() {
        lock1.lock();
        try {

            for (int i = 0; i < 5; i++) {
                System.out.println("this is test2 method A");
                Thread.yield();
            }
        } finally {
            lock1.unlock();
        }
    }

    public void methodB() {
        lock2.lock();
        try {

            for (int i = 0; i < 5; i++) {
                System.out.println("this is test2 method B");
                Thread.yield();
            }
        } finally {
            lock2.unlock();
        }
    }

    public void methodC() {
        lock3.lock();
        try {

            for (int i = 0; i < 5; i++) {
                System.out.println("this is test2 method C");
                Thread.yield();
            }
        } finally {
            lock3.unlock();
        }
    }
}