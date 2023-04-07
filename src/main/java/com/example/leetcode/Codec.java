package com.example.leetcode;

import org.junit.Test;

/**
 * className Codec
 * packageName com.example.leetcode
 * description
 *
 * @author CCC
 * @date 2021/4/27 22:46
 */
public class Codec {

    @Test
    public void test() {

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        String data = serialize(root);

        System.out.println(data);

        TreeNode node = deserialize(data);

        System.out.println(node);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        dfs(root, builder);

        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data.isEmpty()) {
            return null;
        }

        String[] nums = data.split(",");

        TreeNode root = new TreeNode(Integer.parseInt(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            addNode(root, Integer.parseInt(nums[i]));
        }

        return root;
    }

    private void addNode(TreeNode node, int val) {

        if (val <= node.val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
                return;
            }
            addNode(node.left, val);
        } else {
            if (node.right == null) {
                node.right = new TreeNode(val);
                return;
            }
            addNode(node.right, val);
        }
    }

    private void dfs(TreeNode root, StringBuilder builder) {

        if (root == null) {
            return;
        }

        builder.append(root.val).append(",");

        dfs(root.left, builder);

        dfs(root.right, builder);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
