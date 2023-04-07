package com.example.leetcode;

import org.junit.Test;

/**
 * className Simple
 * packageName com.example.leetcode
 * description
 *
 * @author CCC
 * @date 2021/3/17 20:45
 */
public class Simple {

    public static void main(String[] args) {

        Simple simple = new Simple();

        System.out.println(simple.add(2, -1));
    }

    @Test
    public void increasingBST() {

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(7);
        root.right.right.right = new TreeNode(9);

        TreeNode result = increasingBST(root);

        System.out.println(result);
    }

    @Test
    public void isPerfectSquare() {

        int num = 2147395600;

        System.out.println(isPerfectSquare(num));
    }

    @Test
    public void backspaceCompare() {

        String s = "ab#c", t = "ad#c";

        System.out.println(backspaceCompare(s, t));
    }

    public boolean backspaceCompare(String s, String t) {

        StringBuilder sBuilder = new StringBuilder();
        StringBuilder tBuilder = new StringBuilder();

        buildStr(s, sBuilder);
        buildStr(t, tBuilder);

        return sBuilder.toString().equals(tBuilder.toString());
    }

    private void buildStr(String s, StringBuilder builder) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#') {
                if (builder.length() == 0) {
                    continue;
                }
                builder.deleteCharAt(builder.length() - 1);
            } else {
                builder.append(c);
            }
        }
    }

    public boolean isPerfectSquare(int num) {

        int left = 1;
        int right = Math.min(num, 46340);
        while (left < right) {

            int mid = left + (right - left) / 2;

            int val = mid * mid;
            if (val == num) {
                return true;
            } else if (val < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left * left == num;
    }

    TreeNode ans;

    TreeNode cur;

    public TreeNode increasingBST(TreeNode root) {

        dfs(root);

        return ans;
    }

    private void dfs(TreeNode root) {

        if (root == null) {
            return;
        }

        dfs(root.left);

        if (ans == null) {
            ans = new TreeNode(root.val);
            cur = ans;
        } else {
            cur.right = new TreeNode(root.val);
            cur = cur.right;
        }

        dfs(root.right);
    }

    public int add(int a, int b) {

        int tmp = 0;
        while(b != 0) {
            // 本位
            tmp = a ^ b;
            // 进位
            b = (a & b) << 1;
            a = tmp;
        }

        return a;
    }

    class TreeNode {

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

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

}

