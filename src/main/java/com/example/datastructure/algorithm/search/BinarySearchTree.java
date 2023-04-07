package com.example.datastructure.algorithm.search;

import io.vavr.Tuple2;
import net.datafaker.providers.base.Name;
import net.datafaker.transformations.CsvTransformer;
import net.datafaker.transformations.Schema;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StopWatch;

import javax.annotation.processing.Filer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class BinarySearchTree {

    String pathPrefix = "D:/tmp/data/sort/file";
    String pathSuffix = ".csv";

    Node cache;

    public static void main(String[] args) {

//        Node root = new BinarySearchTree().generateIndex("idx_name", 1);
//        try (FileInputStream fis = new FileInputStream("D:/tmp/data/sort/idx_name.obj");
//             ObjectInputStream ois = new ObjectInputStream(fis)) {
//            root = (Node) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        String[] searchKeys = {"宇文昊然", "王耀杰", "许思聪"};

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.preload();
//        binarySearchTree.generateIndex("idx_name", 1);

        StopWatch watch = new StopWatch();
        watch.start();
        List<String> result = binarySearchTree.binarySearch(true, searchKeys);
        watch.stop();
        System.out.println(watch.getLastTaskTimeMillis());
        System.out.println(result);

        watch.start();
        result = binarySearchTree.binarySearch(false, searchKeys);
        watch.stop();
        System.out.println(watch.getLastTaskTimeMillis());
        System.out.println(result);

        watch.start();
        result = binarySearchTree.violentSearch(searchKeys);
        watch.stop();
        System.out.println(watch.getLastTaskTimeMillis());
        System.out.println(result);
    }

    public String findById(int primaryKey) {

        int index = primaryKey / 10000;
        try {
            String result = FileCopyUtils.copyToString(new FileReader(pathPrefix + index + pathSuffix));
            String[] rows = result.split("\r\n");
            return rows[primaryKey % 10000];
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Tuple2<Node, Integer> deserialization(String str, int offset) {

        Node node = new Node();
        // isEnd
        node.isEnd = str.charAt(++offset) == '1';
        offset++;
        // value
        StringBuilder builder = new StringBuilder();
        for (;;) {
            char c = str.charAt(++offset);
            if (c == '[') {
                continue;
            }
            if (str.charAt(offset) == ',' || str.charAt(offset) == ']') {
                if (builder.length() > 0) {
                    node.value.add(Integer.parseInt(builder.toString().trim()));
                    builder.setLength(0);
                }
            } else {
                builder.append(c);
            }
            if (c ==']') {
                break;
            }
        }
        offset++;
        offset++;
        // left
        if (str.charAt(offset) == '-') {
            offset++;
            offset++;
        } else if (str.charAt(offset) == '{') {
            var result = deserialization(str, offset);
            node.left = result._1();
            offset = result._2();
            offset++;
        }
        // right
        if (str.charAt(offset) == '-') {
            offset++;
            offset++;
        } else if (str.charAt(offset) == '{') {
            var result = deserialization(str, offset);
            node.right = result._1();
            offset = result._2();
            offset++;
        }
        return new Tuple2<>(node, offset);
    }

    public void preload() {
        cache = getIndexFromDisk("idx_name");
    }

    public List<String> binarySearch(boolean useIndex, String... strs) {

        List<String> results = new ArrayList<>();
        for (String str : strs) {
            results.addAll(binarySearch(useIndex, str));
        }
        return results;
    }

    public List<String> binarySearch(boolean useIndex, String str) {
        if (cache == null) {
            cache = getIndexFromDisk("idx_name");
        }
        Node root = cache;
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

        if (node.value.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        for (Integer primaryKey : node.value) {
            if (useIndex) {
                result.add(primaryKey + "," + str);
            } else {
                result.add(findById(primaryKey));
            }
        }

        return result;
    }

    public Node getIndexFromDisk(String indexName) {

        Node root = null;
//        try (FileInputStream fis = new FileInputStream("D:/tmp/data/sort/idx_name.obj");
//             ObjectInputStream ois = new ObjectInputStream(fis)) {
//            root = (Node) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
            String result = FileCopyUtils.copyToString(new FileReader("D:/tmp/data/sort/" + indexName + ".txt"));
            root = deserialization(result, 0)._1();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public void generateIndex(String indexName, int index) {
        Node root = generateIndex(index);
        try {
            FileCopyUtils.copy(root.toString(), new FileWriter("D:/tmp/data/sort/" + indexName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //        try (FileOutputStream fos = new FileOutputStream("D:/tmp/data/sort/" + indexName + ".obj");
//             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(root);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public Node generateIndex(int index) {
        Node root = new Node();
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < 100; i++) {
            try {
                String result = FileCopyUtils.copyToString(new FileReader(pathPrefix + i + pathSuffix));
                String[] rows = result.split("\r\n");
                for (String row : rows) {
                    String[] cols = row.split(",");
                    int id = Integer.parseInt(cols[0]);
                    String name = cols[index];
                    binarySearchTree.addNode(root, name, id);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return root;
    }

    public void addNode(Node root, String str, int primaryKey) {
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

    public List<String> violentSearch(String... searchNames) {

        Set<String> names = Arrays.stream(searchNames).collect(Collectors.toSet());

        List<String> results = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                String result = FileCopyUtils.copyToString(new FileReader(pathPrefix + i + pathSuffix));
                String[] rows = result.split("\r\n");
                for (String row : rows) {
                    String[] cols = row.split(",");
                    if (names.contains(cols[1])) {
                        results.add(row);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

}
