package xyz.wenjiesx.demo.designpattern.composite;

/**
 * 组合模式示例
 *
 * @author wenji
 * @date 2019/10/30
 */
public class CompositeTest {

    public static void main(String[] args) {
        Tree tree = new Tree("A");

        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");

        nodeB.add(nodeC);
        tree.root.add(nodeB);

        System.out.println("build tree finish");
    }
}
