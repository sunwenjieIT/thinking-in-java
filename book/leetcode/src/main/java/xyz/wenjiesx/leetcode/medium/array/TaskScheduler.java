package xyz.wenjiesx.leetcode.medium.array;

import java.util.*;

/**
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * <p>
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * <p>
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * 示例 2：
 * <p>
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * 示例 3：
 * <p>
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= task.length <= 104
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wenji
 * @Date 2021/4/27
 */
public class TaskScheduler {

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> taskTypeCountMap = new HashMap<>();
        for (char task : tasks) {
            taskTypeCountMap.put(task, taskTypeCountMap.getOrDefault(task, 0) + 1);
        }
        int size = taskTypeCountMap.size();
        int[] executeTime = new int[size];
        int[] taskCost = new int[size];
        int i = 0;
        Set<Map.Entry<Character, Integer>> entrySet = taskTypeCountMap.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            executeTime[i] = 1;
            taskCost[i] = entry.getValue();
            i++;
        }

        int time = 0;
        for (int t = 0; t < tasks.length; t++) {
            time++;

            int index = -1;
            int cost = 0;
            for (int j = 0; j < size; j++) {
                if (executeTime[j] <= time && cost < taskCost[j]) {
                    index = j;
                    cost = taskCost[j];
                }
            }

            if (index == -1) {
                t--;
                continue;
            }

            taskCost[index] = taskCost[index] - 1;
            executeTime[index] = time + n + 1;
        }
        return time;
    }
}
