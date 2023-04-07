package com.example.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class FreqStack {

    public static void main(String[] args) {

        FreqStack freqStack = new FreqStack();
        freqStack.push (1);
        freqStack.push (0);
        freqStack.push (0);
        freqStack.push (1);
        freqStack.push (5);
        freqStack.push (4);
        freqStack.push (1);
        freqStack.push (5);
        freqStack.push (1);
        freqStack.push (6);
        for (int i = 0; i < 10; i++) {
            System.out.print(freqStack.pop() + ",");
        }
    }

    private PriorityQueue<Node> priorityQueue;

    private Map<Integer, Node> nodes;

    private int index;

    public FreqStack() {
        priorityQueue = new PriorityQueue<>((node1, node2) -> {
            if (node1.stack.size() == node2.stack.size()) {
                return Integer.compare(node2.stack.peek(), node1.stack.peek());
            }
            return Integer.compare(node2.stack.size(), node1.stack.size());
        });
        nodes = new HashMap<>();
    }

    public void push(int val) {

        Node node = nodes.get(val);
        if (node == null) {
            node = new Node(val);
            nodes.put(val, node);
        } else {
            priorityQueue.remove(node);
        }

        node.stack.push(index);

        priorityQueue.offer(node);

        index++;
    }

    public int pop() {
        Node node = priorityQueue.peek();
        if (node.stack.size() == 1) {
            nodes.remove(node.value);
            priorityQueue.poll();
            return node.value;
        }

        priorityQueue.remove(node);

        node.stack.pop();

        priorityQueue.offer(node);

        return node.value;
    }

    static class Node {

        int value;
        Deque<Integer> stack;

        public Node(int value) {
            this.value = value;
            this.stack = new ArrayDeque<>();
        }
    }

}
