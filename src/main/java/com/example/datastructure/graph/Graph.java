package com.example.datastructure.graph;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * className Graph
 * packageName com.example.datastructure.graph
 * description
 *
 * @author CCC
 * @date 2021/1/4 22:48
 */
public class Graph {

    public static void main(String[] args) {

        List<String> vertexes = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");
        Graph graph = new Graph(vertexes.size());
        vertexes.forEach(graph::insertVertex);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.printGraph();

        System.out.println("深度优先遍历");
        graph.dfs();

        System.out.println();

        System.out.println("广度优先遍历");
        graph.bfs();
    }

    public static int INF = Integer.MAX_VALUE;

    /** 图的顶点 */
    private final List<String> vertexes;

    /** 图的边 */
    private final int[][] edges;

    /** 图的顶点最大数量 */
    private final int n;

    /** 图的边的数量 */
    private int edgesCount;

    private boolean[] visible;

    public Graph(int n) {
        super();
        this.n = n;
        this.vertexes = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.edgesCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges[i][j] = INF;
            }
        }
    }

    public Graph(List<String> vertexes, int[][] edges) {
        super();
        this.vertexes = vertexes;
        this.edges = edges;
        this.n = vertexes.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (edges[i][j] != INF) {
                    this.edgesCount++;
                }
            }
        }
    }

    public void printGraph() {
        for (int[] edgeLink : this.edges) {
            System.out.println(Arrays.toString(edgeLink));
        }
    }

    public List<String> getVertexes() {
        return vertexes;
    }

    public int getN() {
        return n;
    }

    public int[][] getEdges() {
        return edges;
    }

    /** 深度遍历 */
    public void dfs() {

        visible = new boolean[n];

        for (int i = 0; i < vertexes.size(); i++) {
            if (!visible[i]) {
                dfs(i);
            }
        }
    }

    /** 广度遍历 */
    public void bfs() {

        visible = new boolean[n];

        for (int i = 0; i < vertexes.size(); i++) {
            if (!visible[i]) {
                bfs(i);
            }
        }
    }

    public void insertVertex(String vertex) {

        ensureMaxSize();

        this.vertexes.add(vertex);
    }

    /**
     * 新增边
     * @param v1 顶点1的索引
     * @param v2 顶点2的索引
     * @param weight 连接关系, 0: 未连接 1：连接
     */
    public void insertEdge(int v1, int v2, int weight) {
        this.edges[v1][v2] = weight;
        this.edges[v2][v1] = weight;
        this.edgesCount++;
    }

    /**
     * @param vertexIndex 顶点索引
     * @return 顶点的第一个邻接节点, 未找到则返回-1
     */
    private int firstNeighbor(int vertexIndex) {

        for (int i = 0; i < n; i++) {
            if (edges[vertexIndex][i] > 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     *
     * @param vertexIndex 顶点索引
     * @param neighborIndex 前一个的邻接节点的索引
     * @return 顶点的当前邻接节点的下一个邻接节点的索引, 未找到则返回-1
     */
    private int nextNeighbor(int vertexIndex, int neighborIndex) {
        for (int i = neighborIndex + 1; i < n; i++) {
            if (edges[vertexIndex][i] > 0) {
                return i;
            }
        }

        return -1;
    }

    private void dfs(int index) {

        String vertex = vertexes.get(index);
        System.out.print(vertex + "->");

        // 标记当前顶点已遍历
        visible[index] = true;

        // 取出当前遍历的顶点的第一个邻接节点
        int w = firstNeighbor(index);
        while (w != -1) {
            // 没有遍历过, 则以第一个邻接节点进行递归遍历
            if (!visible[w]) {
                dfs(w);
            }
            // 已遍历过, 则遍历下一个邻接节点
            w = nextNeighbor(index, w);
        }
    }

    private void bfs(int index) {

        String vertex = vertexes.get(index);
        System.out.print(vertex + "->");

        // 标记当前顶点已遍历
        visible[index] = true;

        LinkedList<Integer> queue = new LinkedList<>();
        // 将当前顶点加入一个队列
        queue.addLast(index);

        while (!queue.isEmpty()) {

            // 从队列头取出并移除一个顶点
            Integer vertexIndex = queue.removeFirst();

            // 取出该顶点的第一个邻接节点
            int w = firstNeighbor(index);
            while (w != -1) {
                if (!visible[w]) {
                    // 加入队列尾
                    queue.addLast(w);
                    System.out.print(vertexes.get(w) + "=>");
                    // 标记当前顶点已遍历
                    visible[w] = true;
                }
                w = nextNeighbor(vertexIndex, w);
            }
        }
    }

    private void ensureMaxSize() {
        Assert.isTrue(this.vertexes.size() < n, "超出顶点最大数量限制");
    }

}
