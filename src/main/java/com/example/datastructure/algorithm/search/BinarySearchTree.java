package com.example.datastructure.algorithm.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    Node root;

    public BinarySearchTree() {
        this.root = new Node();
    }

    public Node getRoot() {
        return root;
    }

    public Node binarySearch(String str) {
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            int codePoint = str.charAt(i);
            for (int j = 31; j >= 0; j--) {
                int bit = (codePoint >> j) & 1;
                if (bit == 0) {
                    if (node.left == null) {
                        break;
                    }
                    node = node.left;
                } else {
                    if (node.right == null) {
                        break;
                    }
                    node = node.right;
                }
            }
        }

        return node;
    }

    public void addNode(String str, int primaryKey) {
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            int codePoint = str.charAt(i);
            for (int j = 31; j >= 0; j--) {
                int bit = (codePoint >> j) & 1;
                if (bit == 0) {
                    if (node.left == null) {
                        node.left = new Node();
                    }
                    node = node.left;
                } else {
                    if (node.right == null) {
                        node.right = new Node();
                    }
                    node = node.right;
                }
            }
            node.isEnd = true;
        }
        node.value.add(primaryKey);
    }

    public static class Node implements Serializable {

        boolean isEnd;
        List<Integer> value;
        Node left;
        Node right;
        public Node() {
            this.value = new ArrayList<>();
        }

        @Override
        public String toString() {
            int isEnd = this.isEnd ? 1 : 0;
            String left = this.left == null ? "-" : this.left.toString();
            String right = this.right == null ? "-" : this.right.toString();
            return "{" +
                    isEnd +
                    "," + value +
                    "," + left +
                    "," + right +
                    '}';
        }
    }

}
