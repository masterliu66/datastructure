package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 */
public class VerifyPostorder {

    @Test
    public void test() {

        int[] validPostOrder = {1,3,2,6,5};
        int[] invalidPostOrder = {1,6,3,2,5};

        assert verifyPostorder(validPostOrder);

        assert !verifyPostorder(invalidPostOrder);
    }

    int end;

    public boolean verifyPostorder(int[] postorder) {

        if (postorder == null || postorder.length == 1) {
            return true;
        }

        // 后序遍历的逆序 [根结点 | 右节点 | 左节点]
        end = postorder.length - 1;

        verifyPostorder(postorder, Integer.MAX_VALUE, Integer.MIN_VALUE);

        return end < 0;
    }

    private void verifyPostorder(int[] postorder, int max, int min) {

        if (end < 0) {
            return;
        }

        int val = postorder[end];
        if (val > max || val < min) {
            return;
        }

        end--;
        // 递归右子树
        verifyPostorder(postorder, max, val);
        // 递归左子树
        verifyPostorder(postorder, val, min);
    }

}
