package com.example.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * className Codec2
 * packageName com.example.leetcode
 * description
 *
 * @author CCC
 * @date 2021/6/30 22:17
 */
public class Codec2 {

    @Test
    public void test() {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

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

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        builder.append(root.val).append(",");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                builder.append(node.left.val).append(",");
            } else {
                builder.append("_").append(",");
            }
            if (node.right != null) {
                queue.add(node.right);
                builder.append(node.right.val).append(",");
            } else {
                builder.append("_").append(",");
            }
        }

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
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!"_".equals(nums[count])) {
                TreeNode newNode = new TreeNode(Integer.parseInt(nums[count]));
                node.left = newNode;
                queue.add(newNode);
            }
            count++;
            if (!"_".equals(nums[count])) {
                TreeNode newNode = new TreeNode(Integer.parseInt(nums[count]));
                node.right = newNode;
                queue.add(newNode);
            }
            count++;
        }

        return root;
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
