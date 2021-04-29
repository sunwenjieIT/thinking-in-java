package xyz.wenjiesx.leetcode.medium.tree.base;

import xyz.wenjiesx.leetcode.medium.tree.BinaryTreeInorderTraversal;

/**
 * 基础二叉树节点
 *
 * @author wenji
 * @Date 2021/4/29
 */
public class TreeNode {

    public int      val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
