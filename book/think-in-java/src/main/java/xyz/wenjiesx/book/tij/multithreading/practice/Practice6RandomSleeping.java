package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 练习6 创建一个任务, 它将睡眠1至10秒之间的随机数量的时间
 * 然后显示它的睡眠时间并退出
 * 创建并运行一定数量的这种任务
 *
 * @author wenji
 * @date 2019/10/17
 */
public class Practice6RandomSleeping implements Runnable {

    private int tid = 0;

    public Practice6RandomSleeping(int tid) {
        this.tid = tid;
    }

    public void run() {

        try {
            int r = (int) (Math.random() * 10 + 1);

            System.out.println("# " + tid + " sleeping " + r + " seconds");
            TimeUnit.MILLISECONDS.sleep(r * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Practice6RandomSleeping(i));
        }
        exec.shutdown();

    }
}
