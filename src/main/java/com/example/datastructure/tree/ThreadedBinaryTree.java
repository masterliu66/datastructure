package com.example.datastructure.tree;

import com.example.datastructure.entity.Entity;

import java.util.function.Consumer;

/**
 * className ThreadedBinaryTree
 * packageName com.example.datastructure.tree
 * description
 *
 * @author CCC
 * @date 2020/12/7 22:07
 */
public class ThreadedBinaryTree<E extends Entity> {

    private Node root;

    /** 在线索化时指向当前结点的前驱节点 */
    private Node pre;

    public void list() {

        if (root == null) {
            System.out.println("[]");
            return;
        }

        infixOrderList(System.out::println);
    }

    public void infixOrderList(Consumer<E> consumer) {

        Node node = root;

        while (node != null) {

            // 找到 leftType = 1 的节点
            while (node.leftType == 0) {
                node = node.left;
            }

            // 进行消费
            consumer.accept(node.element);

            // 如果存在后继结点, 就一直进行消费
            while (node.rightType == 1) {
                node = node.right;
                consumer.accept(node.element);
            }

            // 当前结点移动至右子树
            node = node.right;
        }
    }

    public void threadedNodes() {

        this.pre = null;

        threadedNodes(root);
    }

    private void threadedNodes(Node node) {

        if (node == null) {
            return;
        }

        threadedNodes(node.left);

        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }

        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }

        // 当前结点为下一个结点的前驱节点
        pre = node;

        threadedNodes(node.right);
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public class Node {

        Node left;

        Node right;

        // 0代表left指向左子树, 1代表指向前驱结点
        int leftType;

        // 0代表right指向右子树, 1代表指向后继结点
        int rightType;

        E element;

        public Node(E element) {
            super();
            this.element = element;
            this.leftType = 0;
            this.rightType = 0;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

    }


}
