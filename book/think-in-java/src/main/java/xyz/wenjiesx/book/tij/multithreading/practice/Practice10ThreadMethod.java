package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 练习10
 * 按照ThreadMethod类修改练习5,
 * 使得runTask() 方法将接受一个参数, 表示要计算
 * 总和的斐波那契数字的数量, 并且, 每次调用
 * runTask() 时, 它将返回对submit() 的调用所产生的Future
 *
 * @author wenji
 * @date 2019/10/17
 */
public class Practice10ThreadMethod {

    public Future<Integer> runTask(final int x) {


        ExecutorService exec =
                Executors.newCachedThreadPool();

        Future<Integer> future = exec.submit(new Callable<Integer>() {
            public int f(int y) {
                return y > 2 ? f(y - 1) + f(y - 2) : 1;
            }

            public Integer call() throws Exception {
                int r = 0;
                for (int i = 0; i < x; i++) {
                    r += f(i);
                }
                return r;
            }
        });

        exec.shutdown();
        return future;
    }

    public static void main(String[] args) {


        Future<Integer> future1 = new Practice10ThreadMethod().runTask(10);
        Future<Integer> future2 = new Practice10ThreadMethod().runTask(5);
        Future<Integer> future3 = new Practice10ThreadMethod().runTask(7);

        List<Future<Integer>> results = new ArrayList<Future<Integer>>();

        results.add(future1);
        results.add(future2);
        results.add(future3);

        try {
            for (Future<Integer> future : results) {

                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {

        }

    }

}
