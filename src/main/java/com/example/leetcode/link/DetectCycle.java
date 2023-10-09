package com.example.leetcode.link;

import com.example.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 142. 环形链表 II
 */
public class DetectCycle {

    @Test
    public void test() {
        ListNode node = ListNode.build(new int[]{3, 2, 0, -4});
        assert detectCycle(node) == null;
        node.next.next.next.next = node.next;
        assert detectCycle(node) == node.next;
    }

    public ListNode detectCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        // 有环, 从相遇点到入环点的距离等于从链表头部到入环点的距离
        ListNode node = head;
        while (node != slow) {
            node = node.next;
            slow = slow.next;
        }

        return node;
    }

}
