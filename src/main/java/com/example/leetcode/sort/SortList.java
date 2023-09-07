package com.example.leetcode.sort;

import com.example.leetcode.common.ListNode;
import org.junit.Test;

public class SortList {

    @Test
    public void test() {

        ListNode head = ListNode.build(new int[]{-1, 5, 3, 4, 0});

        sortList2(head);

        System.out.println(head);
    }

    public ListNode sortList(ListNode head) {

        return mergeSort(head, null);
    }

    public ListNode sortList2(ListNode head) {

        ListNode node = head;
        int n = 0;
        while (node != null) {
            node = node.next;
            n++;
        }

        ListNode dummyHead = new ListNode(-1, head);
        for (int len = 1; len < n; len <<= 1) {
            ListNode prev = dummyHead;
            ListNode curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < len && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                // 第一个链表尾断链
                curr.next = null;
                curr = head2;
                // curr可能为空, 说明该长度下只够生成一个链表
                if (curr == null) {
                    prev.next = head1;
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                    break;
                }
                for (int i = 1; i < len && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = curr.next;
                // 第二个链表尾断链
                curr.next = null;
                prev.next = mergeTwoLinkList(head1, head2);
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }

        return dummyHead.next;
    }

    private ListNode mergeSort(ListNode first, ListNode last) {

        if (first == last) {
            return first;
        }

        ListNode fast = first;
        ListNode slow = first;
        while (fast != last && fast.next != last) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode next = slow.next;
        slow.next = null;

        ListNode left = mergeSort(first, slow);

        ListNode right = mergeSort(next, last);

        return mergeTwoLinkList(left, right);
    }

    private ListNode mergeTwoLinkList(ListNode first, ListNode second) {

        ListNode head = new ListNode();
        ListNode tail = head;
        while (first != null && second != null) {
            if (first.val < second.val) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }
        // 将剩余部分追加到合并链表尾部
        tail.next = first == null ? second : first;

        return head.next;
    }

}
