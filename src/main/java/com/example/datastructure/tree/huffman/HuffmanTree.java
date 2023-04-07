package com.example.datastructure.tree.huffman;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * className HuffmanTree
 * packageName com.example.datastructure.tree
 * description
 *
 * @author CCC
 * @date 2020/12/10 23:07
 */
public class HuffmanTree {

    public static void main(String[] args) {

        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        List<Node> nodes = new ArrayList<>(arr.length);
        for (int v : arr) {
            nodes.add(new Node(new ElementImpl(v)));
        }

        Node root = new HuffmanTree().buildHuffmanTree(nodes);

        // 前序遍历赫夫曼树
        root.preOrder(System.out::println);
    }

    private final List<Node> nodes = new ArrayList<>();

    public void add(Element element) {
        this.nodes.add(new Node(element));
    }

    public Node buildHuffmanTree() {
        return buildHuffmanTree(this.nodes);
    }

    /**
     * 构建一颗赫夫曼树
     * @param nodes 要构建的结点列表
     * @return 赫夫曼树的跟结点
     */
    private Node buildHuffmanTree(List<Node> nodes) {

        Assert.isTrue(nodes.size() >= 2, "长度必须要大于等于2");

        while (nodes.size() != 1) {

            // 从小到大进行排序
            Collections.sort(nodes);

            // 取出根节点权值最小的两颗二叉树
            Node node1 = nodes.remove(0);
            Node node2 = nodes.remove(0);

            // 构建一颗新的二叉树，其左右子树分别为上面的两颗二叉树
            Node parent = new Node(new ElementImpl(node1.element.getValue() + node2.element.getValue()));

            parent.left = node1;
            parent.right = node2;

            // 加入到集合末尾
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    static class Node implements Comparable<Node> {

        Node left;
        Node right;

        Element element;

        public Node(Element element) {
            this.element = element;
        }

        public void preOrder(Consumer<Element> consumer) {

            consumer.accept(this.element);

            if (left != null) {
                left.preOrder(consumer);
            }

            if (right != null) {
                right.preOrder(consumer);
            }
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(element.getValue(), o.element.getValue());
        }

    }

    static class ElementImpl implements Element {

        int value;

        public ElementImpl(int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "ElementImpl{" + "value=" + value + '}';
        }
    }

}
