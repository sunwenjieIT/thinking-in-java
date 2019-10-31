package xyz.wenjiesx.demo.performance;

/**
 * %取模和位运算取模性能测试
 *
 * @author wenji
 * @date 2019/10/29
 */
public class ModBitCompare {

    public static long mod(int count) {

        long time1 = System.currentTimeMillis();
        int tmp = 0;
        for (int i = 0; i < count; i++) {
            tmp = 10000 % 1024;
        }
        System.out.println("mod method result: " + tmp);
        long time2 = System.currentTimeMillis();
        return time2 - time1;

    }

    public static long bit(int count) {

        long time1 = System.currentTimeMillis();
        int tmp = 0;
        for (int i = 0; i < count; i++) {
            tmp = 10000 & (1024 - 1);
        }
        System.out.println("bit method result: " + tmp);
        long time2 = System.currentTimeMillis();
        return time2 - time1;

    }

    public static void main(String[] args) {
        int count = 10000 * 10000 ;
        long time1 = mod(count);
        long time2 = bit(count);

        System.out.println("mod method time: " + time1);
        System.out.println("bit method time: " + time2);

    }


}
