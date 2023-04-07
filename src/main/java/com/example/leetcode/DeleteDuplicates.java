package com.example.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，
 * 只保留原始链表中没有重复出现的数字。
 *
 * 返回同样按升序排列的结果链表。
 *
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 *
 * @author CCC
 * @date 2021/3/25 20:32
 */
public class DeleteDuplicates {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        ListNode result = new DeleteDuplicates().deleteDuplicates(head);

        System.out.println(result);
    }

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode curNode = dummy;
        while (curNode.next != null && curNode.next.next != null) {

            int val = curNode.next.val;
            if (curNode.next.next.val == val) {
                while (curNode.next != null && curNode.next.val == val) {
                    curNode.next = curNode.next.next;
                }
                continue;
            }

            curNode = curNode.next;
        }


        return dummy.next;
    }

    public static class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

}
