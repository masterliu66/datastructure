package com.example.leetcode.tree;

import com.example.leetcode.common.TreeNode;
import org.junit.Test;

/**
 * 124. 二叉树中的最大路径和
 */
public class MaxPathSum {

    @Test
    public void test() {

        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxPathSum(root));
    }

    int max = -10001;

    public int maxPathSum(TreeNode root) {

        dfs(root);

        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = Math.max(0, dfs(root.left));
        int rightSum = Math.max(0, dfs(root.right));
        // max( left-root | left-root-right | root | root-right )
        max = Math.max(max, leftSum + root.val + rightSum);
        // max( left-root | root | root-right )
        return root.val + Math.max(leftSum, rightSum);
    }

}
