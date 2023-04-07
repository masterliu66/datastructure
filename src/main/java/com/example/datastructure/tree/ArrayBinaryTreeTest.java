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
public class ArrayBinaryTreeTest {

    public static void main(String[] args) {

        ArrayBinaryTree<User> binaryTree = new ArrayBinaryTree<>();

        binaryTree.add(new User(1, "张3", "张3", "123456"));
        binaryTree.add(new User(2, "张4", "张4", "123456"));
        binaryTree.add(new User(3, "张5", "张5", "123456"));
        binaryTree.add(new User(4, "张6", "张6", "123456"));
        binaryTree.add(new User(5, "张7", "张7", "123456"));
        binaryTree.add(new User(6, "张8", "张8", "123456"));

        binaryTree.infixOrderList();
    }

}
