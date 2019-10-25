package xyz.wenjiesx.book.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 练习27
 * 修改Restaurant.java, 使其使用显示的Lock和Condition对线
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice27Restaurant {
    Meal27 meal;
    boolean isClean = true;

    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson27 waitPerson = new WaitPerson27(this);
    BusBoy27 busBoy = new BusBoy27(this);
    Chef27 chef = new Chef27(this);

    public Practice27Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }

    public static void main(String[] args) {
        new Practice27Restaurant();

    }
}

class Meal27 {
    private final int orderNum;

    public Meal27(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson27 implements Runnable {

    private Practice27Restaurant restaurant;

    protected Lock lock = new ReentrantLock();
    protected Condition condition = lock.newCondition();

    public WaitPerson27(Practice27Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {

                    while (restaurant.meal == null || !restaurant.isClean) {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
                System.out.println("WaitPerson got " + restaurant.meal);
                restaurant.chef.lock.lock();
                try {

                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                } finally {
                    restaurant.chef.lock.unlock();
                }
                restaurant.busBoy.lock.lock();

                try {
                    restaurant.isClean = false;
                    restaurant.busBoy.condition.signalAll();
                } finally {
                    restaurant.busBoy.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }

    }
}

class Chef27 implements Runnable {
    private Practice27Restaurant restaurant;
    private int count = 0;
    protected Lock lock = new ReentrantLock();
    protected Condition condition = lock.newCondition();

    public Chef27(Practice27Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {

        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {

                    while (restaurant.meal != null) {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                System.out.println("Order up!");
                restaurant.waitPerson.lock.lock();
                try {

                    restaurant.meal = new Meal27(count);
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }

    }
}

class BusBoy27 implements Runnable {

    private Practice27Restaurant restaurant;
    protected Lock lock = new ReentrantLock();
    protected Condition condition = lock.newCondition();

    public BusBoy27(Practice27Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {

        try {
            while (!Thread.interrupted()) {

                lock.lock();
                try {

                    while (restaurant.isClean) {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
                System.out.println("BusBoy do clean ");
                TimeUnit.MILLISECONDS.sleep(100);
                restaurant.waitPerson.lock.lock();
                try {

                    restaurant.isClean = true;
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }

            }

        } catch (InterruptedException e) {
            System.out.println("BusBoy interrupted");
        }
    }
}