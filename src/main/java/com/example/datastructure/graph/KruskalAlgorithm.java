package com.example.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.example.datastructure.graph.Graph.INF;

/**
 * className KruskalAlgorithm
 * packageName com.example.datastructure.graph
 * description
 *
 * @author CCC
 * @date 2021/1/14 23:40
 */
public class KruskalAlgorithm {

    public static void main(String[] args) {

        List<String> vertexes = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        int[][] matrix = {
            { 0, 12, INF, INF, INF, 16, 14},
            { 12, 0, 10, INF, INF, 7, INF},
            { INF, 10, 0, 3, 5, 6, INF},
            { INF, INF, 3, 0, 4, INF, INF},
            { INF, INF, 5, 4, 0, 2, 8},
            { 16, 7, 6, INF, 2, 0, 9},
            { 14, INF, INF, INF, 8, 9, 0}};

        Graph graph = new Graph(vertexes, matrix);

        new KruskalAlgorithm().kruskal(graph);
    }

    /**
     * 克鲁斯卡尔算法求最小生成树
     * @param graph 图
     */
    public void kruskal(Graph graph) {

        List<String> vertexes = graph.getVertexes();

        int[][] matrix = graph.getEdges();

        // 构造图的所有边的集合
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for(int j = i + 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != INF) {
                    edges.add(new Edge(new Node(i, vertexes.get(i)), new Node(j, vertexes.get(j)), matrix[i][j]));
                }
            }
        }

        // 按权值大小对边进行排序
        edges.sort(Comparator.comparingInt(Edge::getWeight));

        int size = vertexes.size();

        // 所有顶点在最小生成树中的终点数组
        int[] ends = new int[edges.size()];

        List<Edge> results = new ArrayList<>(size - 1);
        for (Edge edge : edges) {
            int start = edge.start.index;
            int end = edge.end.index;
            // 顶点A的终点
            int m = getEnd(ends, start);
            // 顶点B的终点
            int n = getEnd(ends, end);
            // 两个顶点的终点相同则跳过
            if (m == n) {
                continue;
            }
            results.add(edge);
            // 最小生成树的边树为顶点数 size - 1
            if (results.size() == size - 1) {
                break;
            }
            // 将顶点A的终点设置为顶点B的终点
            ends[m] = n;
        }

        System.out.println(results);
    }

    /**
     * 获得一个顶点的终点索引
     * @param ends 所有顶点的终点数组
     * @param index 顶点索引
     * @return 该顶点的终点索引
     */
    private int getEnd(int[] ends, int index) {

        while (ends[index] != 0) {
            index = ends[index];
        }

        return index;
    }

    static class Edge {

        /** 顶点A */
        Node start;

        /** 顶点B */
        Node end;

        /** 权值 */
        int weight;

        public Edge(Node start, Node end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "<" + start.name +
                    ", " + end.name +
                    "> : " + weight;
        }

    }

    static class Node {

        int index;

        String name;

        public Node(int index, String name) {
            this.index = index;
            this.name = name;
        }
    }

}
