package com.example.datastructure.tree;

import com.example.datastructure.entity.Entity;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * className BinaryTree
 * packageName com.example.datastructure.tree
 * description
 *
 * @author CCC
 * @date 2020/12/4 22:31
 */
public class BinaryTree<E extends Entity> {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void infixOrderList() {

        if (root == null) {
            System.out.println("[]");
            return;
        }

        root.infixOrder(System.out::println);
    }

    public E infixOrderFind(Integer id) {

        if (root == null) {
            return null;
        }

        return root.infixOrderFind(id);
    }

    public void infixOrderRemove(Integer id) {

        if (root == null) {
            return;
        }

        if (Objects.equals(id, root.element.getId())) {
            root = null;
            return;
        }

        root.infixOrderRemove(id, root);
    }

    public class Node {

        Node left;

        Node right;

        E element;

        public Node(E element) {
            this.element = element;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        private void infixOrder(Consumer<E> consumer) {

            if (left != null) {
                left.infixOrder(consumer);
            }

            consumer.accept(element);

            if (right != null) {
                right.infixOrder(consumer);
            }
        }

        private E infixOrderFind(Integer id) {

            if (left != null) {
                E e = left.infixOrderFind(id);
                if (e != null) {
                    return e;
                }
            }

            if (Objects.equals(id, element.getId())) {
                return element;
            }

            if (right != null) {
                return right.infixOrderFind(id);
            }

            return null;
        }

        private void infixOrderRemove(Integer id, Node parent) {

            if (left != null) {
                left.infixOrderRemove(id, this);
            }

            if (Objects.equals(id, element.getId())) {
                parent.removeChild(this);
            }

            if (right != null) {
                right.infixOrderRemove(id, this);
            }
        }

        private void removeChild(Node node) {
            if (left == node) {
                left = null;
            } else if (right == node) {
                right = null;
            }
        }

    }

}
