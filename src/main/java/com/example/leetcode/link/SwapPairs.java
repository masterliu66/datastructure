package com.example.leetcode.link;

import com.example.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 24. 两两交换链表中的节点
 */
public class SwapPairs {

    @Test
    public void test() {

        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});

        ListNode newHead = swapPairs(head);

        System.out.println(newHead);
    }

    public ListNode swapPairs(ListNode head) {

        ListNode dummyNode = new ListNode(-1, head);
        ListNode prev = dummyNode;
        while (prev.next != null && prev.next.next != null) {
            // prev -> node1 -> node2 -> next...
            ListNode node1 = prev.next;
            ListNode node2 = prev.next.next;
            ListNode next = node2.next;
            // prev -> node2 -> next... | node1 -> node2 -> next...
            prev.next = node2;
            // prev -> node2 -> next... | node1 -> next...
            node1.next = next;
            // prev -> node2 -> node1 -> next...
            node2.next = node1;
            // move point
            prev = node1;
        }

        return dummyNode.next;
    }
}
