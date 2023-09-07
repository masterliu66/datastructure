package com.example.leetcode.link;

import com.example.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 206. 反转链表
 */
public class ReverseList {

    @Test
    public void test() {

        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});

        String before = head.toString();

        ListNode newHead = recursive(head);

        String after = newHead.toString();

        assert new StringBuilder(after).reverse().toString().equals(before);
    }

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == head) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            // prev -> node -> next -> ...
            ListNode next = curr.next;
            // prev <- node next -> ...
            curr.next = prev;
            // move point
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public ListNode recursive(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = recursive(head.next);
        // head -> next => next -> head
        head.next.next = head;
        head.next = null;

        return newHead;
    }

}
