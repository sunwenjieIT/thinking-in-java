package xyz.wenjiesx.demo.datastructure;

/**
 * 树节点
 * @author wenji
 * @date 2019/11/4
 */
public class TreeNode {

    int value;
    public TreeNode left;
    public TreeNode right;

    public int leftLen;
    public int rightLen;

    public TreeNode(int value) {
        this.value = value;
    }
}
