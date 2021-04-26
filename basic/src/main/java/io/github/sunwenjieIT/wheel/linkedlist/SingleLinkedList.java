package io.github.sunwenjieIT.wheel.linkedlist;

/**
 * 练习手写单链表
 * 支持新增, 遍历, 反转元素
 *
 * @author wenji
 * @Date 2021/4/25
 */
public class SingleLinkedList<T> {

    int  size;
    Node head;

    class Node {
        T    data;
        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public SingleLinkedList() {
        this.size = 0;
    }

    /**
     * 反转元素 头 -> 尾 尾 -> 头
     */
    public void convertNodes() {
        if (size <= 1)
            return;

        Node t, l = null, r = head;
        for (int i = 0; i < size - 1; i++) {
            t = r.next;
            r.next = l;
            l = r;
            r = t;
        }
        r.next = l;
        head = r;
    }

    public T removeLast() {
        if (head == null) return null;
        Node cur = head;
        Node pre = head;

        while (cur.next != null) {
            pre = cur;
            cur = cur.next;
        }

        pre.next = null;
        size--;
        return cur.data;
    }

    /**
     * 指定坐标插入
     *
     * @param position
     * @param target
     */
    public void add(int position, T target) {
        checkPosition(position);

        Node node = new Node(target);
        if (head == null) {
            head = node;
            size++;
            return;
        }

        Node pre = head, cur = head;
        for (int i = 0; i < position; i++) {
            pre = cur;
            cur = cur.next;
        }

        node.next = cur;
        pre.next = node;

        size++;
    }

    /**
     * 尾插
     * @param target
     */
    public void addLast(T target) {
        add(size, target);
    }

    /**
     * 头插
     * @param target
     */
    public void add(T target) {
        Node cur = head;
        Node node = new Node(target);
        node.next = cur;
        head = node;
        size++;
    }


    public T get(int position) {
        checkPosition(position);

        Node cur = head;
        for (int i = 1; i <= position; i++) {
            cur = cur.next;
        }

        return cur.data;
    }

    @Override
    public String toString() {

        Node cur = head;
        StringBuilder sb = new StringBuilder("[");
        while (cur != null) {
            sb.append(cur.data).append(" ");
            cur = cur.next;
        }
        return sb.append("]").toString();
    }

    protected void checkPosition(int position) {
        if (position > size) {
            throw new IndexOutOfBoundsException("index, " + position + ", size: " + size);
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(3, 10);

        System.out.println(list);

        list.convertNodes();
        System.out.println("convert nodes");
        System.out.println(list);
        System.out.println(list.get(2));
        System.out.println(list.removeLast());
        System.out.println(list);
    }

}
