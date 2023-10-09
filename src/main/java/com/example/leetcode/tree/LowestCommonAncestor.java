package com.example.leetcode.tree;

import com.example.leetcode.common.TreeNode;
import org.junit.Test;

/**
 * 236. 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {

    @Test
    public void test() {
        // [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode node = lowestCommonAncestor(root, root.left, root.right);

        assert node.val == 3;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {
            // 1)一个是另外一个的祖先
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            // 2)左右两边各自有一个o1、o2，返回这个祖先"
            return root;
        }
        // 1) / 2) 找不到，回溯时一直是null，如果找到了，那么将找到的值往上窜！
        return left != null ? left : right;
    }

}
