package xyz.wenjiesx.leetcode.medium;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -10的9次方 <= nums[i]<= 109
 * nums是一个非递减数组
 * -10的9次方<= target<= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wenji
 * @Date 2021/4/24
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        //                  0  1  2  3  4  5  6  7  8
        int[] t = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(searchRange(t, 8));

    }

    public static int[] searchRange(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while(head <= tail){
            int index = (head + tail) / 2;
            int num   = nums[index];
            if (num == target) {
                break;
            } else if (num > target) {
                tail = index - 1;
            } else {
                head = index + 1;
            }
        }
        if (head > tail)
            return new int[]{-1,-1};

        if (head < tail) {
            while (nums[head] != nums[tail]) {
                if (nums[head] != target) {
                    head++;
                }
                if (nums[tail] != target) {
                    tail--;
                }
            }
        }
        return new int[]{head, tail};
    }

}
