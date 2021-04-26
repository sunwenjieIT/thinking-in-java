package io.github.sunwenjieIT.wheel.linkedlist;

/**
 * 基于单链表实现LRU算法 不考虑并发
 *
 * @author wenji
 * @Date 2021/4/26
 */
public class LruLinkedList<T> extends SingleLinkedList<T> {

    int limit;
    private static final int DEFAULT_MEMORY_LIMIT = 5;
    public LruLinkedList(int limit) {
        this.limit = limit;
    }

    public LruLinkedList() {
        this(DEFAULT_MEMORY_LIMIT);
    }

    public void addLru(T target) {
        if (super.size >= limit) {
            removeLast();
        }
        add(target);
    }

    public T getLru(int index) {
        checkPosition(index);

        Node pre = head, cur = head;
        for (int i = 0; i < index; i++) {
            pre = cur;
            cur = cur.next;
        }

        pre.next = cur.next;
        cur.next = head;
        head = cur;
        return cur.data;
    }

    public static void main(String[] args) {
        LruLinkedList<Integer> list = new LruLinkedList<>();
        list.addLru(1);
        list.addLru(2);
        list.addLru(3);
        list.addLru(4);
        list.addLru(5);

        System.out.println(list.toString());

        list.getLru(2);
        System.out.println(list.toString());
        list.addLru(10);
        System.out.println(list.toString());
    }
}
