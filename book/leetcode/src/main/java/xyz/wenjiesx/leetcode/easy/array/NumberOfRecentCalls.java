package xyz.wenjiesx.leetcode.easy.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 *
 * 请你实现 RecentCounter 类：
 *
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 *
 * 解释：
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
 *  
 *
 * 提示：
 *
 * 1 <= t <= 109
 * 保证每次对 ping 调用所使用的 t 值都 严格递增
 * 至多调用 ping 方法 104 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-recent-calls
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author wenji
 * @Date 2021/4/27
 */
public class NumberOfRecentCalls {

    public static void main(String[] args) {
        RecentCounter2 recentCounter = new RecentCounter2();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(100));
        System.out.println(recentCounter.ping(3001));
        System.out.println(recentCounter.ping(3002));
    }

    static class RecentCounter {

        int[] objs;
        int capacity;
        int size;

        public RecentCounter() {
            size = 0;
            capacity = 10;
            objs = new int[10];
        }

        public void add(int t) {
            objs[size] = t;
            size++;
            if (size == capacity) {
                //扩容
                int[] newObjs = new int[capacity * 2];
                for (int i = 0; i < objs.length; i++) {
                    newObjs[i] = objs[i];
                }
                objs = newObjs;
                capacity = capacity * 2;
            }
        }

        public int count(int t) {
            int m = size;
            for (int i = 0; i < size; i++) {
                if (objs[i] >= t - 3000) {
                    m = i;
                    break;
                }
            }
            return size - m;
        }

        public int ping(int t) {
            add(t);
            return count(t);
        }
    }

    static class RecentCounter2 {

        public RecentCounter2() {
            capacity = 3001;
        }

        int  capacity;
        int  size;
        Node head;
        Node tail;

        public int ping(int t) {
            add(t);
            if (size > capacity) {
                removeLast();
            }

            while (tail.v < t - 3000) {
                removeLast();
            }

            return size;
        }

        public void removeLast() {
            if (tail == null)
                return;

            Node pre = tail.pre;
            if (pre != null) {
                pre.next = null;
                tail.pre = null;
                tail = pre;
            }
            size--;
        }

        public void add(int t) {
            Node newNode = new Node(t);
            newNode.next = head;
            if (head != null) {
                head.pre = newNode;
            }
            head = newNode;
            if (tail == null)
                tail = newNode;

            size++;
        }

        class Node {
            int v;
            Node next;
            Node pre;

            public Node(int v) {
                this.v = v;
            }
        }
    }

    static class RecentCounter3 {

        Queue<Integer> queue;

        public RecentCounter3() {
            this.queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.add(t);
            if (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

}
