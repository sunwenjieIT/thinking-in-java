package xyz.wenjiesx.book.multithreading.practice;

/**
 * 创建一个任务, 可以产生由n个斐波那契数字组成的序列, n通过构造器提供
 * 使用线程创建大量这种任务并驱动
 * @author wenji
 * @date 2019/10/15
 */
public class Practice2Fibonacci implements Runnable {

    private int x;

    public Practice2Fibonacci(int x) {
        this.x = x;
    }

    public int f(int y) {
        return y > 2 ? f(y - 1) + f(y - 2) : 1;
    }

    public void run() {

        for (int i = 1; i <= x; i++) {
            System.out.println(f(i));
        }

    }

    public static void main(String[] args) {
        new Thread(new Practice2Fibonacci(8)).start();
    }
}
