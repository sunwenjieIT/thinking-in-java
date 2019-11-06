package xyz.wenjiesx.leetcode.easy;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * @author wenji
 * @Date 2019/11/6
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {

        if (strs.length == 0)
            return "";

        if (strs.length == 1)
            return strs[0];

        int index = 0;
        String tmp = "";
        StringBuffer buffer = new StringBuffer("");
        while (true) {


            for (int i = 0; i < strs.length; i++) {
                String s = strs[i];
                if (s.length() == index) {
                    return buffer.toString();
                }

                if (i == 0) {
                    tmp = s.substring(index, index + 1);
                } else if (i > 0 && !tmp.equals(s.substring(index, index + 1))) {
                    return buffer.toString();
                }

            }
            buffer.append(tmp);
            index++;

        }

    }
}
