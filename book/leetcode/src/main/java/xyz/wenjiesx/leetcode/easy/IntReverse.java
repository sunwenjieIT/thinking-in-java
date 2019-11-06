package xyz.wenjiesx.leetcode.easy;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2的31次方,  2的31次方 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author wenji
 * @Date 2019/11/6
 */
public class IntReverse {
    public int reverse(int x) {

        boolean isMinus = x < 0;
        String s = x + "";
        s = isMinus ? s.substring(1) : s;

        char tmp;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {

            tmp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = tmp;

        }
        Integer re;
        try {
            re = Integer.valueOf(String.valueOf(chars));
        } catch (NumberFormatException e) {
            return 0;
        }
        re = isMinus ? -re : re;

        return re;
    }
}
