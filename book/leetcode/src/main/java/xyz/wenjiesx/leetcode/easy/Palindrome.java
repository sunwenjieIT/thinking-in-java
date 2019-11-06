package xyz.wenjiesx.leetcode.easy;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * @author wenji
 * @Date 2019/11/6
 */
public class Palindrome {
    public boolean isPalindrome(int x) {

        if(x < 0)
            return false;

        String s = x + "";
        int left = 1;
        int right = s.length();

        while (left < right) {
            if (get(x, left) != get(x, right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public int get(int x, int i) {
        int pow = (int) Math.pow(10, i);
        if (pow == Integer.MAX_VALUE) {
            return x / (int) Math.pow(10, i - 1);
        }
        int v = x % pow / (pow / 10);

        return v;
    }
}
