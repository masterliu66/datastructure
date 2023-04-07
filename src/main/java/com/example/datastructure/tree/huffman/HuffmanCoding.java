package com.example.datastructure.tree.huffman;

import cn.hutool.core.lang.Assert;

import java.util.*;

/**
 * className HuffmanCoding
 * packageName com.example.datastructure.tree.huffman
 * description
 *
 * @author CCC
 * @date 2020/12/13 23:02
 */
public class HuffmanCoding {

    public static void main(String[] args) {

        String content = "i like like like java do you like a java";

        byte[] contentBytes = content.getBytes();

        System.out.println("content bytes : " + Arrays.toString(contentBytes));

        HuffmanCodingEncoder encoder = new HuffmanCodingEncoder(contentBytes);

        byte[] codingBytes = encoder.encode();

        System.out.println("coding bytes : " + Arrays.toString(codingBytes));

        System.out.println("压缩率 = " + String.format("%.2f",
                ((double)(contentBytes.length - codingBytes.length) / (double) contentBytes.length)));

        byte[] decodeBytes = new HuffmanCodingDecoder(codingBytes, encoder.huffmanCodesTable, encoder.length).decode();

        System.out.println("decode content : " + new String(decodeBytes));
    }

    public static class HuffmanCodingEncoder {

        /** 要进行编码的byte数组 */
        private final byte[] contentBytes;

        /** 赫夫曼树的根结点 */
        private HuffmanTree.Node root;

        /** 赫夫曼编码表 */
        private Map<Byte, String> huffmanCodesTable;

        /** 编码后的二进制字符串长度 */
        private int length;

        public HuffmanCodingEncoder(byte[] contentBytes) {
            super();
            this.contentBytes = contentBytes;
        }

        public byte[] encode() {

            buildHuffmanTree();

            generateHuffmanCodeTable();

            return getCodingBytes();
        }

        /**
         * 根据要进行编码的byte数组创建一颗赫夫曼树
         */
        private void buildHuffmanTree() {

            // 所有不同字符出现次数统计表
            Map<Byte, Integer> countOfChars = new HashMap<>(16);
            for (byte b : contentBytes) {
                Integer count = countOfChars.computeIfAbsent(b, key -> 0);
                countOfChars.put(b, ++count);
            }

            HuffmanTree huffmanTree = new HuffmanTree();
            countOfChars.forEach((key, value) -> huffmanTree.add(new DataElement(key, value)));

            this.root = huffmanTree.buildHuffmanTree();
        }

        /**
         * 根据一颗赫夫曼树生产一个赫夫曼编码表
         */
        private void generateHuffmanCodeTable() {

            Assert.notNull(root, "赫夫曼树的根节点不能为空");

            StringBuilder baseBuilder = new StringBuilder();

            this.huffmanCodesTable = new HashMap<>(16);

            // 向左递归
            generateHuffmanCodeTable("0", root.left, baseBuilder, huffmanCodesTable);

            // 向右递归
            generateHuffmanCodeTable("1", root.right, baseBuilder, huffmanCodesTable);
        }

        private void generateHuffmanCodeTable(
                String code, HuffmanTree.Node node,
                StringBuilder baseBuilder,
                Map<Byte, String> huffmanCodesTable) {

            if (node == null) {
                return;
            }

            StringBuilder builder = new StringBuilder(baseBuilder);

            builder.append(code);

            // 是否为叶子结点
            if (node.element instanceof DataElement) {
                DataElement dataElement = (DataElement) node.element;
                huffmanCodesTable.put(dataElement.data, builder.toString());
            } else {
                // 向左递归
                generateHuffmanCodeTable("0", node.left, builder, huffmanCodesTable);
                // 向右递归
                generateHuffmanCodeTable("1", node.right, builder, huffmanCodesTable);
            }
        }

        private byte[] getCodingBytes() {

            StringBuilder builder = new StringBuilder();
            for (byte b : contentBytes) {
                builder.append(huffmanCodesTable.get(b));
            }

            String codingContent = builder.toString();

            int length = codingContent.length();
            byte[] codingBytes = new byte[(length + 7) / 8];

            int count = 0;
            for (int i = 0; i < length;i += 8) {
                String byteStr = i + 8 < length ? codingContent.substring(i, i + 8) : codingContent.substring(i);
                codingBytes[count++] = (byte) Integer.parseInt(byteStr, 2);
            }

            this.length = length;

            return codingBytes;
        }

        public Map<Byte, String> getHuffmanCodesTable() {
            return huffmanCodesTable;
        }

        public int getLength() {
            return length;
        }

    }

    public static class HuffmanCodingDecoder {

        /** 赫夫曼编码后的byte数组 */
        private final byte[] codingBytes;

        /** 赫夫曼编码表 */
        private final Map<Byte, String> huffmanCodesTable;

        /** 编码后的二进制字符串长度 */
        private final int length;

        public HuffmanCodingDecoder(byte[] codingBytes, Map<Byte, String> huffmanCodesTable, int length) {
            super();
            this.codingBytes = codingBytes;
            this.huffmanCodesTable = huffmanCodesTable;
            this.length = length;
        }

        public byte[] decode() {

            String decodeContent = getDecodeContent();

            return getDecodeBytes(decodeContent);
        }

        private String getDecodeContent() {

            boolean lastByteComplete = codingBytes.length * 8 == length;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < codingBytes.length; i++) {

                // 赫夫曼编码后的byte数组最后一个byte可能未占满
                if (!lastByteComplete && i == codingBytes.length - 1) {
                    builder.append(Integer.toBinaryString(codingBytes[i]));
                } else {
                    String binaryString = Integer.toBinaryString(codingBytes[i] | 0x100);
                    builder.append(binaryString.substring(binaryString.length() - 8));
                }
            }

            return builder.toString();
        }

        public byte[] getDecodeBytes(String decodeContent) {

            // 以赫夫曼编码表的值为键, 键为值重新生成一个解码表
            Map<String, Byte> huffmanDecodeTable = new HashMap<>(huffmanCodesTable.size());
            huffmanCodesTable.forEach((k, v) -> huffmanDecodeTable.put(v, k));

            List<Byte> list = new ArrayList<>();

            int cursor = 0;
            int len = 1;
            // 移动游标, 依次从解码表中找到对应的byte
            while (cursor + len <= decodeContent.length()) {

                String content = decodeContent.substring(cursor, cursor + len);
                Byte b = huffmanDecodeTable.get(content);
                if (b == null) {
                    len++;
                } else {
                    list.add(b);
                    cursor += len;
                    len = 1;
                }
            }

            // 将list转换为一个byte数组
            byte[] decodeBytes = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                decodeBytes[i] = list.get(i);
            }

            return decodeBytes;
        }

    }

    static class DataElement implements Element {

        byte data;

        int count;

        public DataElement(byte data, int value) {
            super();
            this.data = data;
            this.count = value;
        }

        @Override
        public int getValue() {
            return count;
        }

        @Override
        public String toString() {
            return "DataElement{" +
                    "data=" + data +
                    ", count=" + count +
                    '}';
        }
    }


}
