package xyz.wenjiesx.leetcode.easy.linkedlist;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 * <p>
 * 提示：
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * * Definition for singly-linked list.
 * * public class ListNode {
 * *     int val;
 * *     ListNode next;
 * *     ListNode() {}
 * *     ListNode(int val) { this.val = val; }
 * *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * * }
 *
 * @author wenji
 * @Date 2021/4/25
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
//        l2.next = new ListNode(3);
//        l2.next.next = new ListNode(4);

        ListNode node = mergeTwoLists(l1, l2);

        do {
            if (node != null) {
                System.out.println(node.val);
                node = node.next;
            }
        } while (node != null);

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        boolean  isStartByL1 = l1.val <= l2.val;
        ListNode left        = null, right = isStartByL1 ? l1 : l2;
        ListNode iterator    = isStartByL1 ? l2 : l1;

        do {
            if (right == null) {
                left.next = iterator;
                break;
            } else if (right.val >= iterator.val && left != null) {
                ListNode tmp = iterator.next;
                iterator.next = right;
                left.next = iterator;
                left = iterator;
                iterator = tmp;
            } else {
                left = right;
                right = right.next;
            }
        } while (iterator != null);

        return isStartByL1 ? l1 : l2;
    }

    public static class ListNode {
        int      val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
