package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * 练习5
 *  修改练习2, 使计算所有斐波那契数字的数值总和的任务成为callable.
 *  创建多个任务并显示结果.
 *
 * @author wenji
 * @date 2019/10/16
 */
public class Practice5FibonacciCallable implements Callable<String> {

    private int x;

    public Practice5FibonacciCallable(int x) {
        this.x = x;
    }

    public int f(int y) {
        return y > 2 ? f(y - 1) + f(y - 2) : 1;
    }

    public String call() throws Exception {

        int r = 0;
        for (int i = 1; i <= x; i++) {
            r += f(i);
        }

        return x + " count Fibonacci number sum result is: " + r;
    }

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        List<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++) {
            Future<String> future = exec.submit(new Practice5FibonacciCallable(10));
            results.add(future);
        }
        for (Future<String> fs : results) {

            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }

        }

    }
}
