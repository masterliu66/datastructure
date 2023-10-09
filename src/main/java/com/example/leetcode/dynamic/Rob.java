package com.example.leetcode.dynamic;

import com.example.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 */
public class Rob {

    @Test
    public void test() {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        System.out.println(rob(root));
    }

    // map[node]表示以node作为根节点时可以抢到的最高金额
    Map<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {

        postOrder(root);

        return map.get(root);
    }

    private void postOrder(TreeNode root) {

        if (root.left != null) {
            postOrder(root.left);
        }

        if (root.right != null) {
            postOrder(root.right);
        }

        // 不抢root节点
        int val1 = childrenSum(root);
        // 抢root节点
        int val2 = root.val + childrenSum(root.left) + childrenSum(root.right);
        map.put(root, Math.max(val1, val2));
    }

    private int childrenSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return map.getOrDefault(root.left, 0) + map.getOrDefault(root.right, 0);
    }

}
