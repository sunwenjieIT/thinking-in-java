package xyz.wenjiesx.book.multithreading.practice;

/**
 * 21.2.2 练习1
 * 实现一个runnable, 在run内打印消息然后调用yield
 * 重复操作3次
 * 从run返回
 * 在构造器中放置一条启动消息, 并且放置一条在任务终止时的关闭消息
 * 使用线程创建大量的这种任务并驱动他们
 *
 * @author wenji
 * @date 2019/10/15
 */
public class Practice1Runnable implements Runnable {

    private int id;
    public Practice1Runnable(int id) {
        this.id = id;
        System.out.println("message by runnable constructor, id: " + id);
    }

    public void run() {

        for (int i = 0; i < 3; i++) {

            System.out.println("message by runnable run method. id: " + id);
            // 让步机制 对线程调度器的建议, 建议让出cpu, 但完全是选择性的. 没有任何部分可以保障完全执行.
            Thread.yield();
        }

        System.out.println("thread " + id + " finish");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Practice1Runnable(i)).start();
        }
    }
}
