package xyz.wenjiesx.demo.designpattern.composite;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Tree {

    TreeNode root = null;

    public Tree(String name) {
        this.root = new TreeNode(name);
    }

}
