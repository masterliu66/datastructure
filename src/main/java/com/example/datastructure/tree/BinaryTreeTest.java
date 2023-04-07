package com.example.datastructure.tree;

import com.example.code.spring.bean.User;

/**
 * className BinaryTreeTest
 * packageName com.example.datastructure.tree
 * description
 *
 * @author CCC
 * @date 2020/12/4 22:31
 */
public class BinaryTreeTest {

    public static void main(String[] args) {

        BinaryTree<User> binaryTree = new BinaryTree<>();

        BinaryTree<User>.Node root = binaryTree.new Node(
                new User(1, "张3", "张3", "123456"));
        BinaryTree<User>.Node node2 = binaryTree.new Node(
                new User(2, "张4", "张4", "123456"));
        BinaryTree<User>.Node node3 = binaryTree.new Node(
                new User(3, "张5", "张5", "123456"));
        BinaryTree<User>.Node node4 = binaryTree.new Node(
                new User(4, "张6", "张6", "123456"));
        BinaryTree<User>.Node node5 = binaryTree.new Node(
                new User(5, "张7", "张7", "123456"));
        BinaryTree<User>.Node node6 = binaryTree.new Node(
                new User(6, "张8", "张8", "123456"));

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        binaryTree.setRoot(root);

        binaryTree.infixOrderList();

        User user = binaryTree.infixOrderFind(4);
        System.out.println("find " + user);

        binaryTree.infixOrderRemove(3);

        binaryTree.infixOrderList();
    }

}
