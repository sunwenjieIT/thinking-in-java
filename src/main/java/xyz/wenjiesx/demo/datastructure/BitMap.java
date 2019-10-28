package xyz.wenjiesx.demo.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * bitmap的java实现 基于byte[]
 * 从0开始作为第一个数字
 * @author wenji
 * @date 2019/10/28
 */
public class BitMap {

    int length;
    byte[] bits;

    public BitMap(int length) {
        this.length = length;

        this.bits = new byte[getIndex(length) + 1];
    }

    private int getIndex(int num) {
        return num / 8;
    }

    private int getPosition(int num) {
        return num % 8;
//        return num & 0x07;
    }

    public void setBit(int num) {
        bits[getIndex(num)] |= 1 << getPosition(num);
    }

    public boolean contains(int num) {
        int result = bits[getIndex(num)] & 1 << getPosition(num);
        return result != 0;
    }

    public void delBit(int num) {
        bits[getIndex(num)] &= ~(1 << getPosition(num));
    }

    public List<Integer> list() {

        List<Integer> sortNums = new ArrayList<>();

        for (int i = 0; i < bits.length; i++) {
            byte bit = bits[i];
            int tmp = 1;
            for (int j = 0; j < 8; j++) {
                if ((bit & tmp) != 0) {
                    sortNums.add(i * 8 + j);
                }
                tmp = tmp << 1;
            }
        }
        return sortNums;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(20000);
        bitMap.setBit(20000);
        bitMap.setBit(500);
        bitMap.setBit(6666);
        bitMap.setBit(0);
        System.out.println(bitMap.contains(500));;
        System.out.println(bitMap.contains(20000));;
        System.out.println(bitMap.contains(6666));;
        System.out.println(bitMap.list());
        bitMap.delBit(6);
        bitMap.delBit(6666);
        System.out.println(bitMap.contains(6666));

        List<Integer> list = bitMap.list();
        System.out.println(list);

//        测试数组越界的情况
        BitMap bit = new BitMap(7);
        bit.setBit(7);
        bit.setBit(8);

    }
}
