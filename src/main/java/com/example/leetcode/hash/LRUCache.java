package com.example.leetcode.hash;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 */
public class LRUCache {

    @Test
    public void test() {
        init(2);
        // 缓存是 [1=1]
        put(1, 1);
        // 缓存是 [2=2, 1=1]
        put(2, 2);
        // 缓存是 [1=1, 2=2]
        assert get(1) == 1;
        // 该操作会使得关键字 2 作废，缓存是 [1=1, 3=3]
        put(3, 3);
        assert get(2) == -1;
        // 该操作会使得关键字 1 作废，缓存是 [4=4, 3=3]
        put(4, 4);
        assert get(1) == -1;
        assert get(3) == 3;
        assert get(4) == 4;
    }

    private Map<Integer, Node> map;

    private final Node head = new Node();

    private final Node tail = new Node();

    private int capacity;

    public LRUCache() {
        this.head.next = tail;
        this.tail.prev = head;
    }

    public void init(int capacity) {
        this.map = HashMap.newHashMap(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        removeNode(node);
        insertHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            addNode(key, value);
        } else {
            node.value = value;
            removeNode(node);
            insertHead(node);
        }
    }

    private void addNode(int key, int value) {
        Node node;
        node = new Node();
        node.key = key;
        node.value = value;
        insertHead(node);
        map.put(key, node);
        if (map.size() > capacity) {
            removeLastNode();
        }
    }

    /**
     * 插入队列头
     */
    private void insertHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeLastNode() {
        map.remove(tail.prev.key);
        removeNode(tail.prev);
    }

    /**
     * 删除节点
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }
}
