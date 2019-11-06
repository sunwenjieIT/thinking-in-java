package xyz.wenjiesx.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 *
 * @author wenji
 * @Date 2019/11/6
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {

        int a;
        int b;
        int c;
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < len - 2; i++) {

            a = nums[i];

            if (a > 0)
                break;

            if (i > 0 && a == nums[i-1])
                continue;

            int L = i + 1, R = len - 1;

            if (a + nums[L] > 0)
                break;

            while (L < R) {



                if (L > i + 1 && nums[L] == nums[L-1]) {
                    L++;
                    continue;
                }


                if (R < len - 1 && nums[R] == nums[R+1]) {
                    R--;
                    continue;
                }


                if(nums[L] + nums[R] == -nums[i]) {
                    List<Integer> re = new ArrayList<>();
                    re.add(a);
                    re.add(nums[L]);
                    re.add(nums[R]);
                    result.add(re);
                    L++;
                    R--;
                } else if (nums[L] + nums[R] > -nums[i]) {
                    R--;
                } else {
                    L++;
                }
            }


            /**
             for (int j = i + 1; j < len - 1; j++) {

             b = nums[j];
             if (a + b > 0)
             break;

             if (nums[j] == nums[j-1] && j > i + 1)
             continue;

             for (int k = j + 1; k < len; k++) {
             c = nums[k];
             if (a + b + c > 0)
             break;

             if (c == nums[k-1] && k > j + 1)
             continue;

             if (a + b + c == 0){
             List<Integer> re = new ArrayList<>();
             re.add(a);
             re.add(b);
             re.add(c);
             result.add(re);
             }
             }
             } **/
        }
        return result;
    }
}
