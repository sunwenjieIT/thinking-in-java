package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 练习26
 * 在Restaurant.java中添加一个BusBoy类
 * 在上菜之后, WaitPerson应该通知BusBoy清理
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice26Restaurant {

    Meal26 meal;
    boolean isClean = true;

    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson26 waitPerson = new WaitPerson26(this);
    BusBoy26 busBoy = new BusBoy26(this);
    Chef26 chef = new Chef26(this);

    public Practice26Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }

    public static void main(String[] args) {
        new Practice26Restaurant();

    }


}

class Meal26 {
    private final int orderNum;

    public Meal26(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson26 implements Runnable {

    private Practice26Restaurant restaurant;

    public WaitPerson26(Practice26Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null || !restaurant.isClean) {
                        wait();
                    }
                }
                System.out.println("WaitPerson got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
                synchronized (restaurant.busBoy) {
                    restaurant.isClean = false;
                    restaurant.busBoy.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }

    }
}

class Chef26 implements Runnable {
    private Practice26Restaurant restaurant;
    private int count = 0;

    public Chef26(Practice26Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {

        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                }
                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                System.out.println("Order up!");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal26(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }

    }
}

class BusBoy26 implements Runnable {

    private Practice26Restaurant restaurant;

    public BusBoy26(Practice26Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {

        try {
            while (!Thread.interrupted()) {

                synchronized (this) {
                    while (restaurant.isClean) {
                        wait();
                    }
                }
                System.out.println("BusBoy do clean ");
                TimeUnit.MILLISECONDS.sleep(100);
                synchronized (restaurant.waitPerson) {
                    restaurant.isClean = true;
                    restaurant.waitPerson.notifyAll();
                }

            }

        } catch (InterruptedException e) {
            System.out.println("BusBoy interrupted");
        }
    }
}