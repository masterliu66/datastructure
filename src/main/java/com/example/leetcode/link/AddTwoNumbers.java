package com.example.leetcode.link;

import com.example.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 2. 两数相加
 */
public class AddTwoNumbers {

    @Test
    public void test() {
        ListNode listNode = addTwoNumbers(ListNode.build(new int[]{2, 4, 3}), ListNode.build(new int[]{5, 6, 4}));
        System.out.println(listNode);

        listNode = addTwoNumbers(ListNode.build(new int[]{0}), ListNode.build(new int[]{0}));
        System.out.println(listNode);

        listNode = addTwoNumbers(ListNode.build(new int[]{9,9,9,9,9,9,9}), ListNode.build(new int[]{9,9,9,9}));
        System.out.println(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode();
        int carry = 0;
        ListNode curr = dummyHead;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = sum / 10;
            sum = sum % 10;
            curr.next = new ListNode(sum);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            curr = curr.next;
        }

        if (carry == 1) {
            curr.next = new ListNode(1);
        }

        return dummyHead.next;
    }

}
