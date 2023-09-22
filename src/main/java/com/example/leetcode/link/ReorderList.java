package com.example.leetcode.link;

import com.example.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 143. 重排链表
 */
public class ReorderList {

    @Test
    public void test() {
        ListNode head = ListNode.build(new int[]{1, 2, 3});
        reorderList(head);
        assert "1,3,2".equals(head.toString());

        head = ListNode.build(new int[]{1, 2, 3, 4});
        reorderList(head);
        assert "1,4,2,3".equals(head.toString());
    }

    public void reorderList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = findMid(head);
        ListNode newHead = reverseList(mid.next);
        mid.next = null;
        mergeList(head, newHead);
    }

    private ListNode findMid(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            // prev -> curr -> next -> ...
            ListNode next = curr.next;
            // prev <- curr | next -> ...
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    private void mergeList(ListNode first, ListNode second) {
        while (first != null && second != null) {
            // first -> next1 -> ... | second -> next2 -> ...
            ListNode next1 = first.next;
            ListNode next2 = second.next;
            // first -> second -> next1 -> ... | next2 -> ...
            first.next = second;
            second.next = next1;
            // move point
            first = next1;
            second = next2;
        }
    }

}
