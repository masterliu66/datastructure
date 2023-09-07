package com.example.leetcode.link;

import com.example.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 25. K 个一组翻转链表
 */
public class ReverseKGroup {

    @Test
    public void test() {

        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});

        ListNode newHead = reverseKGroup(head, 2);

        System.out.println(newHead);
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == head || k <= 0) {
            return head;
        }

        int n = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            n++;
        }

        ListNode dummyHead = new ListNode(-1, head);

        ListNode start = dummyHead;
        // 最多可以分n/k组
        for (int i = 0; i < n / k; i++) {
            // start -> firstNode -> ... -> nodek -> tail
            ListNode firstNode = start.next;
            ListNode prev = firstNode;
            ListNode curr = prev.next;
            // 翻转链表
            for (int j = 0; j < k - 1; j++) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            // start -> firstNode | nodek -> ... -> firstNode | tail
            // start -> nodek -> ... -> firstNode | tail
            start.next = prev;
            // start -> nodek -> ... -> firstNode -> tail
            firstNode.next = curr;
            // move point
            start = firstNode;
        }

        return dummyHead.next;
    }

}
