package com.example.leetcode.common;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this(val);
        this.next = next;
    }

    public static ListNode build(int[] nums) {
        ListNode head = new ListNode();
        ListNode tail = head;
        for (int num : nums) {
            tail.next = new ListNode(num);
            tail = tail.next;
        }
        return head.next;
    }

    @Override
    public String toString() {
        return val + (next != null ? "," + next : "");
    }
}
