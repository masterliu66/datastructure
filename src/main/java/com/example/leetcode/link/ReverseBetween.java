package com.example.leetcode.link;

import com.example.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 92. 反转链表 II
 */
public class ReverseBetween {

    @Test
    public void test() {

        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});

        ListNode newHead = reverseBetween(head, 2, 4);

        System.out.println(newHead);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || head.next == head) {
            return head;
        }

        ListNode dummyHead = new ListNode(-1, head);
        ListNode start = dummyHead;
        for (int i = 1; i < left; i++) {
            start = start.next;
        }
        // start -> left -> ... -> (right) -> tail -> ...
        ListNode leftNode = start.next;
        ListNode prev = leftNode;
        ListNode curr = leftNode.next;
        // start -> left | (right) -> ... -> left | tail -> ...
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        start.next = prev;

        leftNode.next = curr;

        return dummyHead.next;
    }

}
