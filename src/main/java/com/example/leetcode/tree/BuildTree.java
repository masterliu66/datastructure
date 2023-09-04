package com.example.leetcode.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    @Test
    public void test() {

        // inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        TreeNode root = buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});

        // [3,9,20,null,null,15,7]
        System.out.println(root);
    }

    int[] postorder;

    Map<Integer, Integer> inorderIndexes = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        int n = postorder.length;
        if (n == 0) {
            return null;
        }

        this.postorder = postorder;

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexes.put(inorder[i], i);
        }

        // 后序遍历的逆序[中 | 右 | 左]
        // 中序遍历的逆序[左 | 中 | 右]
        return buildTree(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int inorderStart, int inorderEnd, int postorderStart, int postorderEnd) {

        if (postorderStart > postorderEnd) {
            return null;
        }

        int rootVal = postorder[postorderEnd];

        Integer inorderRoot = inorderIndexes.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        int rightNodeCount = inorderEnd - inorderRoot;
        // 递归构建左子树
        root.left = buildTree(inorderStart, inorderRoot - 1, postorderStart, postorderEnd - rightNodeCount - 1);
        // 递归构建右子树
        root.right = buildTree(inorderRoot + 1, inorderEnd, postorderEnd - rightNodeCount, postorderEnd - 1);

        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
