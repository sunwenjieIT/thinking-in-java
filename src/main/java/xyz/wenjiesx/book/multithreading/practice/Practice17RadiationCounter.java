package xyz.wenjiesx.book.multithreading.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 练习17
 * 创建一个辐射计数器
 * 它可以具有任意数量的传感器
 *
 * @author wenji
 * @date 2019/10/20
 */
public class Practice17RadiationCounter {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exec.execute(new Sensor(i));
        }

        TimeUnit.SECONDS.sleep(3);
        Sensor.cancel();
        exec.shutdown();

        boolean b = exec.awaitTermination(200, TimeUnit.MILLISECONDS);
        if (!b) {
            System.out.println("some tasks were not terminated!");

        }
        System.out.println("total: " + Sensor.getTotalCount());
        System.out.println("sum of sensors: " + Sensor.sumSensors());

    }
}


class RadCount {
    private int count = 0;
    private Random rand = new Random();
    public synchronized int increment() {
        return count++;
    }
    public synchronized int value() {
        return count;
    }
}

class Sensor implements Runnable{
    private static RadCount radCount = new RadCount();
    private static List<Sensor> sensors = new ArrayList<Sensor>();
    private int number = 0;
    private final int id;
    private static volatile boolean canceled = false;
    public static void cancel() {
        canceled = true;
    }

    public Sensor(int id) {
        this.id = id;
        sensors.add(this);
    }


    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " total : " + radCount.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
                e.printStackTrace();
            }

            System.out.println("stopping " + this);
        }
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id " + id +
                ":" + getValue() +
                '}';
    }

    public static int getTotalCount() {
        return radCount.value();
    }

    public static int sumSensors() {
        int sum = 0;
        for (Sensor sensor : sensors) {
            sum += sensor.getValue();
        }
        return sum;
    }

}

