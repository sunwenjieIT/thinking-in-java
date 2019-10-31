package xyz.wenjiesx.book.multithreading.practice;

/**
 *  * 练习24
 *  * 使用wait和notifyAll解决单个生产者
 *  * 单个消费者问题. 生产者不能溢出接受者的缓冲区
 *  * 而这在生产者比消费者速度快时有可能发生.
 *  * 如果消费者比生产者速度快, 那么消费者不能读取多次先沟通数据
 *  * 不要对生产者和消费者的相对速度做任何假设.
 *
 * @author wenji
 * @date 2019/10/21
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

class Item {
    private final int itemNum;
    public Item(int itemNum) { this.itemNum = itemNum; }
    @Override
    public String toString() { return "Item " + itemNum; }
}

class Producer implements Runnable {
    private int count = 0;
    Market24 market;
    Producer(Market24 m) { market = m; }
    protected int getCount() { return count; }
    public void run() {
        while(!Thread.interrupted()) {
            try {
                while(count < 100) {
                    Item item = new Item(++count);
                    if(market.storage.offer(item)) {
                        println("Produced " + item);
                        // Notify consumer that Item available:
                        synchronized(market.consumer) {
                            market.consumer.notifyAll();
                        }
                    }
                    // Storage holds only 10 Items:
                    synchronized(this) {
                        while(!(market.storage.size() < 10)) {
                            wait();
                        }
                    }
                }
                // Stop production after 100 Items:
                println("Produced " + count + " Items"
                        + "\nStopping production");
                market.exec.shutdownNow();
            } catch(InterruptedException e) {
                println("Producer interrupted");
                println("Produced " + count + " Items");
            }
        }
    }
}

class Consumer implements Runnable {
    int consumed = 0;
    Market24 market;
    List<Item> cart = new ArrayList<Item>();
    Consumer(Market24 m) { market = m; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Cannot consume more Items than produced:
                synchronized(this) {
                    while(!(cart.size() < market.producer.getCount())) {
                        wait();
                    }
                }
                // Move Item from storage to cart:
                if(cart.add(market.storage.poll())) {
                    println("Consuming Item " + ++consumed);
                    // Notify producer - ready for more:
                    synchronized(market.producer) {
                        market.producer.notifyAll();
                    }
                }
            }
        }
        catch(InterruptedException e) {
            println("Consumer interrupted");
            println("Consumed " + consumed + " Items");
        }
    }
}


public class Market24 {
    ExecutorService exec = Executors.newCachedThreadPool();
    Queue<Item> storage = new LinkedList<Item>();
    Producer producer = new Producer(this);
    Consumer consumer = new Consumer(this);
    public Market24() {
        exec.execute(producer);
        exec.execute(consumer);
    }
    public static void main(String[] args) {
        new Market24();
    }
}
