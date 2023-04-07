package com.example.datastructure.tree;

import com.example.datastructure.entity.SimpleNodeValue;
import com.example.datastructure.entity.Value;

import java.util.function.Consumer;

/**
 * className BinarySortTree
 * packageName com.example.datastructure.tree
 * description
 *
 * @author CCC
 * @date 2020/12/16 22:14
 */
public class BinarySortTree<V extends Value> {

    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2, 11};

        BinarySortTree<SimpleNodeValue> binarySortTree = new BinarySortTree<>();

        for (int v : arr) {
            binarySortTree.add(new SimpleNodeValue(v));
        }

        binarySortTree.infixOrderList();

        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(10);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);
        binarySortTree.delNode(6);

        System.out.println("删除结点后: ");

        binarySortTree.infixOrderList();

    }

    protected Node root;

    public void add(V value) {

        if (value == null) {
            return;
        }

        Node node = new Node(value);

        if (root == null) {
            root = node;
            return;
        }

        root.add(node);
    }

    public void delNode(int value) {

        if (root == null) {
            return;
        }

        // 要删除的结点为root结点
        if (root.getValue() == value) {
            delRootNode();
            return;
        }

        root.delNode(value);
    }

    private void delRootNode() {

        // root结点无子结点, 直接置空
        if (this.root.left == null && this.root.right == null) {
            this.root = null;
            return;
        }

        // root结点有两个子结点, 删除右子树中权值最小的结点, 并将其值替换至root结点
        if (this.root.left != null && this.root.right != null) {
            Node minValueRightNode = this.root.delMinValueRightNode();
            this.root.value = minValueRightNode.value;
            return;
        }

        // root结点只有一个子结点, 将root结点指向该子结点
        if (this.root.left != null) {
            this.root = this.root.left;
        } else {
            this.root = this.root.right;
        }

    }

    public void infixOrderList() {

        if (root == null) {
            System.out.println("[]");
            return;
        }

        root.infixOrder(System.out::println);
    }


    protected class Node {

        protected Node left;

        protected Node right;

        protected V value;

        public Node(V value) {
            this.value = value;
        }

        protected void delNode(int value) {

            Node parent = searchParent(value);
            if (parent == null) {
                System.out.printf("未找到权值为%d的结点\n", value);
                return;
            }

            // 要删除的结点为parent的左子结点
            if (parent.left != null && parent.left.getValue() == value) {

                delNode(parent, parent.left, true);

            // 要删除的结点为parent的右子结点
            } else {

                delNode(parent, parent.right, false);

            }

        }

        /**
         * 从二叉排序树中删除一个结点
         * @param parent 要删除的结点的父结点
         * @param target 要删除的结点
         * @param left 要删除的结点是否为父结点的左结点
         */
        private void delNode(Node parent, Node target, boolean left) {

            // 要删除的结点为叶子结点
            if (target.left == null && target.right == null) {
                if (left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                return;
            }

            // 要删除的结点有两个子结点
            if (target.left != null && target.right != null) {

                Node minValueRightNode = target.delMinValueRightNode();

                if (left) {
                    parent.left.value = minValueRightNode.value;
                } else {
                    parent.right.value = minValueRightNode.value;
                }

                return;
            }

            // 要删除的结点只有一个子结点
            Node node = target.left != null ? target.left : target.right;
            if (left) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }

        private Node delMinValueRightNode() {
            return delMinValueRightNode(this);
        }

        /**
         * 删除一个结点的右子树中的最小权值的结点
         * @param node 结点
         * @return 被删除的结点
         */
        private Node delMinValueRightNode(Node node) {

            Node target = node.right;
            while (target.left != null) {
                target = target.left;
            }

            delNode(target.getValue());

            return target;
        }

        /**
         * 根据权值查找一个结点的父结点
         * @param value 结点权值
         * @return 权值为value的父结点
         */
        private Node searchParent(int value) {

            if (this.left != null && this.left.getValue() == value) {
                return this;
            }

            if (this.right != null && this.right.getValue() == value) {
                return this;
            }

            Node node = null;

            if (this.left != null) {
                node = this.left.searchParent(value);
            }

            if (node != null) {
                return node;
            }

            if (this.right != null) {
                node = this.right.searchParent(value);
            }

            return node;
        }

        protected void add(Node node) {

            if (node == null) {
                return;
            }

            if (node.getValue() < this.getValue()) {
                if (this.left == null) {
                    this.left = node;
                    return;
                }
                this.left.add(node);
            } else {
                if (this.right == null) {
                    this.right = node;
                    return;
                }
                this.right.add(node);
            }

        }

        protected void infixOrder(Consumer<V> consumer) {

            if (left != null) {
                left.infixOrder(consumer);
            }

            consumer.accept(value);

            if (right != null) {
                right.infixOrder(consumer);
            }
        }

        public int getValue() {
            return this.value.getValue();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

    }

}
