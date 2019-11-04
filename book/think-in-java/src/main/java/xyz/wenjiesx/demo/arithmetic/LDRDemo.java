package xyz.wenjiesx.demo.arithmetic;

/**
 * 二叉树中序遍历
 *
 * @author wenji
 * @date 2019/11/4
 */
public class LDRDemo {

    public int data;
    public LDRDemo leftChild;
    public LDRDemo rightChild;

    public LDRDemo(int data) {
        this.data = data;
    }

    public static void inOrderTraversal(LDRDemo node) {
        if (node == null)
            return;

        inOrderTraversal(node.leftChild);
        System.out.println(node.data);
        inOrderTraversal(node.rightChild);
    }

    public static void main(String[] args) {
        LDRDemo node1 = new LDRDemo(2);
        LDRDemo node2 = new LDRDemo(5);
        LDRDemo node3 = new LDRDemo(27);
        LDRDemo node4 = new LDRDemo(9);
        LDRDemo node5 = new LDRDemo(33);
        LDRDemo node6 = new LDRDemo(23);
        LDRDemo node7 = new LDRDemo(31);

        node1.leftChild = node2;
        node2.leftChild = node3;
        node2.rightChild = node4;
        node1.rightChild = node5;
        node5.leftChild = node6;
        node6.leftChild = node7;

        inOrderTraversal(node1);


    }

}
