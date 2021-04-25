package xyz.wenjiesx.leetcode.medium.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenji
 * @Date 2021/4/25
 */
public class CopyListWithRandomPointer {

    public static void main(String[] args) {

        //[[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node head = new Node(7);
//        new Node(13);

    }

    private static Map<Node, Node> map = new HashMap<>();

    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        if (map.containsKey(head))
            return map.get(head);

        Node n = new Node(head.val);
        map.put(head, n);
        n.next = copyRandomList(head.next);
        n.random = copyRandomList(head.random);

        return n;
    }

    public static class Node {
        int  val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
