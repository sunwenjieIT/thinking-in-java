package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

/**
 * 练习25
 * 在Restaurant.java的Chef类中
 * 在调用shutdownNow后从run中return, 观察行为上的差异
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice25Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson25 waitPerson = new WaitPerson25(this);
    Chef25 chef = new Chef25(this);

    public Practice25Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Practice25Restaurant();
    }


}

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson25 implements Runnable {
    private Practice25Restaurant restaurant;

    public WaitPerson25(Practice25Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait(); // ... for the chef to produce a meal
                    }
                }
                println("WaitPerson25 got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // ready for another
                }
            }
        } catch (InterruptedException e) {
            println("WaitPerson25 interrupted");
        }
    }
}

class Chef25 implements Runnable {
    private Practice25Restaurant restaurant;
    private int count = 0;

    public Chef25(Practice25Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait(); // ... for the meal to be taken
                    }
                }
                if (++count == 10) {
                    println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                    return; // now Chef25 won't make another meal
                }
                println("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            println("Chef25 interrupted");
        }
    }
}