package com.example.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 剑指 Offer 07. 重建二叉树
 */
public class BuildTree {

    @Test
    public void test() {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode treeNode = buildTree(preorder, inorder);
        // Output: [3,9,20,null,null,15,7]
        System.out.println(treeNode);
        assert "[3,9,20,null,null,15,7]".equals(treeNode.toString());
    }

    private final Map<Integer, Integer> iorderMap = new HashMap<>();

    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        for (int i = 0; i < n; i++) {
            iorderMap.put(inorder[i], i);
        }
        this.preorder = preorder;
        return buildTree(0, n - 1, 0);
    }

    private TreeNode buildTree(int preorderLeft, int preorderRight, int inorderLeft) {

        if (preorderLeft > preorderRight) {
            return null;
        }

        int rootVal = preorder[preorderLeft];
        int inorderRoot = iorderMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        int leftNodeCount = inorderRoot - inorderLeft;
        root.left = buildTree(preorderLeft + 1, preorderLeft + leftNodeCount, inorderLeft);
        root.right = buildTree(preorderLeft + leftNodeCount + 1, preorderRight, inorderRoot + 1);

        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(this);
            StringBuilder builder = new StringBuilder("[");
            builder.append(this.val).append(",");
            while (!queue.isEmpty()) {
                List<TreeNode> list = new ArrayList<>();
                for (int i = 0, s = queue.size(); i < s; i++) {
                    list.add(queue.poll());
                }
                boolean isLeaf = true;
                for (TreeNode node : list) {
                    if (node.left != null || node.right != null) {
                        isLeaf = false;
                        break;
                    }
                }
                if (isLeaf) {
                    break;
                }
                for (TreeNode node : list) {
                    if (node.left == null) {
                        builder.append("null").append(",");
                    } else {
                        builder.append(node.left.val).append(",");
                        queue.add(node.left);
                    }
                    if (node.right == null) {
                        builder.append("null").append(",");
                    } else {
                        builder.append(node.right.val).append(",");
                        queue.add(node.right);
                    }
                }
            }

            builder.setCharAt(builder.length() - 1, ']');

            return builder.toString();
        }
    }

}