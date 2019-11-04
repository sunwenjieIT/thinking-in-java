package xyz.wenjiesx.demo.arithmetic;

import com.sun.jmx.remote.internal.ArrayQueue;
import xyz.wenjiesx.demo.datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 二叉树相关计算
 * 计算深度 宽度 最大长度
 *
 * @author wenji
 * @date 2019/11/4
 */
public class TreeNodeArithmetic {


    public static TreeNode makeTree() {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(27);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(33);
        TreeNode node6 = new TreeNode(23);
        TreeNode node7 = new TreeNode(31);
        TreeNode node8 = new TreeNode(32);
        TreeNode node9 = new TreeNode(33);
        TreeNode node10 = new TreeNode(34);
        TreeNode node11 = new TreeNode(35);
        TreeNode node12 = new TreeNode(36);


        node1.left = node2;
        node2.left = node3;
        node2.right = node4;
        node1.right = node5;
        node5.left = node6;
        node6.left = node7;
        node5.right = node8;
        node8.right = node9;
        node9.left = node10;
        node7.right = node11;
        node11.left = node12;

        return node1;
    }

    public static void main(String[] args) {
        TreeNode root = makeTree();
        int maxDepth = getMaxDepth(root);
        int maxWidth = getMaxWidth(root);
        System.out.println("tree depth:" + getMaxPath(root));
        System.out.println("tree width:" + maxWidth);

        ;
        System.out.println("tree max path:" + maxPath);
    }

    public static int maxPath = 0;

    public static int getMaxPath(TreeNode root) {
        if (root == null)
            return 0;

        int left = getMaxPath(root.left);
        int right = getMaxPath(root.right);
        if (left + right > maxPath)
            maxPath = left + right;

        return 1 + Math.max(left, right);
    }

    public static int getMaxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int left = getMaxDepth(root.left);
        int right = getMaxDepth(root.right);
        return 1 + Math.max(left, right);

    }

    public static int getMaxWidth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new ArrayDeque<>();
        int maxWitdth = 1; // 最大宽度
        queue.add(root); // 入队

        while (true) {
            int len = queue.size(); // 当前层的节点个数
            if (len == 0)
                break;
            while (len > 0) {// 如果当前层，还有节点
                TreeNode t = queue.poll();
                len--;
                if (t.left != null)
                    queue.add(t.left); // 下一层节点入队
                if (t.right != null)
                    queue.add(t.right);// 下一层节点入队
            }
            maxWitdth = Math.max(maxWitdth, queue.size());
        }
        return maxWitdth;

    }

}
