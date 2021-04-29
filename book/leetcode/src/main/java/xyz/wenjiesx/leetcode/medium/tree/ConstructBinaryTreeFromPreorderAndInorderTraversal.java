package xyz.wenjiesx.leetcode.medium.tree;

import xyz.wenjiesx.leetcode.medium.tree.base.TreeNode;

import java.util.*;

/**
 * @author wenji
 * @Date 2021/4/29
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9, new TreeNode(10), new TreeNode(12, new TreeNode(13), new TreeNode(17)));
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7, null, new TreeNode(21)));

        List<Integer> inorderList  = BinaryTreeInorderTraversal.inorderTraversal(root);
        List<Integer> preorderList = BinaryTreePreorderTraversal.preorderTraversal(root);

        TreeNode treeNode = buildTree(list2Array(preorderList), list2Array(inorderList));
        System.out.println(BinaryTreePreorderTraversal.preorderTraversal(treeNode));
        System.out.println(BinaryTreeInorderTraversal.inorderTraversal(treeNode));

    }

    public static int[] list2Array(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
/*

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        List<Integer> preorderList = new ArrayList<>();
        List<Integer> inorderList = new ArrayList<>();
        for (int i : preorder) {
            preorderList.add(i);
        }

        for (int i : inorder) {
            inorderList.add(i);
        }

        return buildTree(preorderList, inorderList);
    }
*/


    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        /**
         List<Integer> preorderList = new ArrayList<>();
         List<Integer> inorderList = new ArrayList<>();
         for (int i : preorder) {
         preorderList.add(i);
         }

         for (int i : inorder) {
         inorderList.add(i);
         }
         **/
        Map<Integer, Integer> inorderValIndexMap = new HashMap<>();
        int                   size               = inorder.length;
        for (int i = 0; i < size; i++) {
            inorderValIndexMap.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, inorderValIndexMap, 0, size - 1, 0, size - 1);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder, Map<Integer,Integer> inorderValIndexMap, int preorderLeft, int preorderRight,
                              int inorderLeft, int inorderRight) {

        //前序 [ 中 [左集合] [右集合]]
        //中序 [[左集合] 中 [右集合]]
        //中间值
        if (preorderLeft > preorderRight)
            return null;

        int mid = preorder[preorderLeft];
        int midIndexInOrder = inorderValIndexMap.get(mid);
        TreeNode node = new TreeNode();

        node.val = mid;
        node.left = buildTree(preorder, inorder, inorderValIndexMap, preorderLeft + 1, preorderLeft + midIndexInOrder - inorderLeft,
                inorderLeft, midIndexInOrder - 1);
        node.right = buildTree(preorder, inorder, inorderValIndexMap, preorderLeft + midIndexInOrder - inorderLeft + 1, preorderRight,
                midIndexInOrder + 1, inorderRight);

        return node;
    }

    public static TreeNode buildTree(List<Integer> preorder, List<Integer> inorder) {
        //构造中序值索引 便于查找mid值
        if (preorder.size() == 0)
            return null;

        Map<Integer, Integer> inorderValIndexMap = new HashMap<>();
        int                   size               = inorder.size();
        for (int i = 0; i < size; i++) {
            inorderValIndexMap.put(inorder.get(i), i);
        }

        //前序 [ 中 [左集合] [右集合]]
        //中序 [[左集合] 中 [右集合]]
        //中间值
        int mid = preorder.get(0);

        Integer midIndexInorder = inorderValIndexMap.get(mid);

        TreeNode node = new TreeNode();
        node.val = mid;
        node.left = buildTree(preorder.subList(1, midIndexInorder + 1), inorder.subList(0, midIndexInorder));
        node.right = buildTree(preorder.subList(midIndexInorder + 1, size), inorder.subList(midIndexInorder + 1, size));

        return node;
    }

}
