package io.github.sunwenjieIT.wheel.linkedlist;

/**
 * 练习手写单链表
 * 支持新增, 遍历, 反转元素
 *
 * @author wenji
 * @Date 2021/4/25
 */
public class SingleLinkedList<T> {

    int capacity;

    Node<T> first;

    public static class Node<T> {
        T       data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public SingleLinkedList() {
        this.capacity = 0;
    }

    /**
     * 反转元素 头 -> 尾 尾 -> 头
     */
    public void convertNodes() {
        if (capacity <= 1)
            return;

        Node<T> t, l = null, r = first;
        for (int i = 0; i < capacity - 1; i++) {
            t = r.next;
            r.next = l;
            l = r;
            r = t;
        }
        r.next = l;
        first = r;
    }

    public void add(int position, T target) {
        if (position > capacity) {
            throw new RuntimeException("out of index, " + position + ", capacity: " + capacity);
        }

        Node<T> node = new Node<>(target);
        if (position == 0) {
            node.next = first;
            first = node;
        } else {
            Node<T> tmp = first;
            for (int i = 1; i < position; i++) {
                tmp = tmp.next;
            }
            if (tmp.next != null)
                node.next = tmp.next;

            tmp.next = node;
        }
        capacity++;
    }

    public void add(T target) {
        add(capacity, target);
    }

    public T get(int position) {
        if (position > capacity - 1) {
            throw new RuntimeException("out of index, " + position + ", capacity: " + capacity);
        }
        Node<T> tmp = first;
        for (int i = 1; i <= position; i++) {
            tmp = tmp.next;
        }

        return tmp.data;
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(3, 10);

        for (int i = 0; i < list.capacity; i++) {
            System.out.println(list.get(i));
        }

        list.convertNodes();
        System.out.println("convert nodes");
        for (int i = 0; i < list.capacity; i++) {
            System.out.println(list.get(i));
        }
    }
}
