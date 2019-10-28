package xyz.wenjiesx.demo.sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wenji
 * @date 2019/10/28
 */
public class MergeSort {
    public static int[] mergeSort(int[] nums, int l, int h) {
        if (l == h)
            return new int[]{nums[l]};

        int mid = l + (h - l) / 2;
        int[] leftArr = mergeSort(nums, l, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, h); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        return newNum;
    }

    /**
     * 多路归并排序模拟实现
     *
     * @param lists
     * @return
     */
    public static List<Integer> mergeSort(List<Integer>... lists) {

        List<Integer> result = new ArrayList<>();

        Integer tmp = null;

        List<Integer> emptyIndex = new ArrayList<>();

        int tmpIndex = 0;
        while (emptyIndex.size() < lists.length) {

            for (int i = 0; i < lists.length; i++) {

                if (emptyIndex.contains(i))
                    continue;

                List<Integer> list = lists[i];
                if (tmp == null || tmp > list.get(0)) {
                    tmp = list.get(0);
                    tmpIndex = i;
                }

            }

            result.add(tmp);
            System.out.println("tmp: " + tmp + " tmpIndex: " + tmpIndex);
            lists[tmpIndex].remove(0);
            tmp = null;
            if (lists[tmpIndex].isEmpty())
                emptyIndex.add(tmpIndex);


        }

        return result;
    }

    /**
     * 两路归并排序
     *
     * @param nums1 有序集合1
     * @param nums2 有序集合2
     * @return 合并排序后的有序集合
     */
    public static List<Integer> mergeSort(int[] nums1, int[] nums2) {

        int length1 = nums1.length;
        int length2 = nums2.length;
        List<Integer> result = new ArrayList<>();

        int a = 0, b = 0;

        while (a < length1 && b < length2) {
            int i = nums1[a];
            int j = nums2[b];
            if (i > j) {
                result.add(j);
                b++;
            } else {
                result.add(i);
                a++;
            }

        }
        if (a == length1) {
            List<Integer> collect = Arrays.stream(nums2).boxed().collect(Collectors.toList()).subList(b, length2);

            result.addAll(collect);
        } else {
            List<Integer> collect = Arrays.stream(nums1).boxed().collect(Collectors.toList()).subList(a, length1);
            result.addAll(collect);

        }

        return result;

    }


    public static void main(String[] args) {
        int[] nums = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 10};
        int[] newNums = mergeSort(nums, 0, nums.length - 1);
        for (int x : newNums) {
            System.out.println(x);
        }

        int[] a = new int[]{1, 3, 5, 7, 9, 21, 30, 40, 50};
        int[] b = new int[]{2, 4, 6, 10, 30};
        List<Integer> result = mergeSort(a, b);
        System.out.println(result);

        List<Integer> listA = new ArrayList<>(Arrays.asList(1, 3, 6, 10,23,32));
        List<Integer> listB = new ArrayList<>(Arrays.asList(2, 60, 67, 89));
        List<Integer> listC = new ArrayList<>(Arrays.asList(5, 12, 45, 90, 211, 235, 236));

        List<Integer> integers = mergeSort(listA, listB, listC);
        System.out.println(integers);
    }
}
