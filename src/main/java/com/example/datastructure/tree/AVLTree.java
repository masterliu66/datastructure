package com.example.datastructure.tree;

import com.example.datastructure.entity.SimpleNodeValue;
import com.example.datastructure.entity.Value;

/**
 * className AVLTree
 * packageName com.example.datastructure.tree
 * description
 *
 * @author CCC
 * @date 2020/12/18 23:11
 */
public class AVLTree<V extends Value> extends BinarySortTree<V> {

    public static void main(String[] args) {

        int[] arr = {4, 6, 8, 5, 9};
        AVLTree<SimpleNodeValue> avlTree = new AVLTree<>();
        for (int i : arr) {
            avlTree.add(new SimpleNodeValue(i));
        }

        avlTree.infixOrderList();

        System.out.println("左子树高度: " + avlTree.getLeftHeight());
        System.out.println("右子树高度: " + avlTree.getRightHeight());
    }

    @Override
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

    public int getLeftHeight() {
        return ((Node) root).leftHeight();
    }

    public int getRightHeight() {
        return ((Node) root).rightHeight();
    }

    class Node extends BinarySortTree<V>.Node {

        public Node(V value) {
            super(value);
        }

        @Override
        protected void add(BinarySortTree<V>.Node node) {
            super.add(new Node(node.value));
            if (leftHeight() - rightHeight() > 1) {
                leftRotate();
            } else if (rightHeight() - leftHeight() > 1) {
                rightRotate();
            }
        }

        /**
         * 左旋
         */
        private void leftRotate() {

            // 以当前的结点的value创建一个新结点
            Node newNode = new Node(value);

            // 新结点的左指针指向当前结点的左子结点
            newNode.left = left;

            // 新节点的右指针指向当前结点的右子结点的左子结点
            newNode.right = right.left;

            // 将当前结点的值替换为右子结点的值
            value = right.value;

            // 当前结点的左指针指向新结点
            left = newNode;

            // 当前结点的右指针指向右子结点的右子结点
            right = right.right;
        }

        /**
         * 右旋
         */
        private void rightRotate() {

            // 以当前的结点的value创建一个新结点
            Node newNode = new Node(value);

            // 新节点的左指针指向当前结点的左子结点的右子结点
            newNode.left = left.right;

            // 新结点的右指针指向当前结点的右子结点
            newNode.right = right;

            // 将当前结点的值替换为左子结点的值
            value = left.value;

            // 当前结点的左指针指向左子结点的左子结点
            left = left.left;

            // 当前结点的右指针指向新结点
            right = newNode;
        }

        /**
         * @return 以当前结点为根结点树的高度
         */
        protected int height() {
            return Math.max(left == null ? 0 : ((Node) left).height(), right == null ? 0 : ((Node) right).height()) + 1;
        }

        /**
         * @return 当前结点为根结点树的左子树高度
         */
        protected int leftHeight() {

            if (left == null) {
                return 0;
            }

            return ((Node) left).height();
        }

        /**
         * @return 当前结点为根结点树的右子树高度
         */
        protected int rightHeight() {

            if (right == null) {
                return 0;
            }

            return ((Node) right).height();
        }

    }

}
