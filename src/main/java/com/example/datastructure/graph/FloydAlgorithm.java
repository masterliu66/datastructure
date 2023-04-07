package com.example.datastructure.graph;

import java.util.Arrays;
import java.util.List;

/**
 * className FloydAlgorithm
 * packageName com.example.datastructure.graph
 * description
 *
 * @author CCC
 * @date 2021/1/17 23:07
 */
public class FloydAlgorithm {

    private final static int N = 65535;

    public static void main(String[] args) {

        List<String> vertexes = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        int size = vertexes.size();

        int[][] matrix = new int[size][size];

        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        Graph graph = new Graph(vertexes, matrix);

        new FloydAlgorithm(graph).floyd();
    }

    private final Graph graph;

    /** 顶点到其他各顶点的最短距离 */
    private final int[][] dis;

    /** 当前被访问顶点的前驱结点 */
    private final String[][] pre;

    public FloydAlgorithm(Graph graph) {
        this.graph = graph;
        this.dis = new int[graph.getN()][graph.getN()];
        this.pre = new String[graph.getN()][graph.getN()];
        int[][] edges = graph.getEdges();
        List<String> vertexes = graph.getVertexes();
        for (int i = 0; i < dis.length; i++) {
            System.arraycopy(edges[i], 0, dis[i], 0, dis[i].length);
            Arrays.fill(pre[i], vertexes.get(i));
        }
    }

    /**
     * 弗洛伊德算法求图的所有顶点到其他各顶点的最短距离
     */
    public void floyd() {

        List<String> vertexes = graph.getVertexes();

        int size = vertexes.size();

        // 被访问的中间顶点
        for (int k = 0; k < size; k++) {
            // 起始访问顶点
            for (int i = 0; i < size; i++) {
                // 终点顶点
                for (int j = 0; j < size; j++) {
                    // 起始访问顶点到中间顶点的距离 + 中间顶点到终点顶点的距离
                    int len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        // 更新起始顶点到终点顶点的距离, 并记录前驱结点
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }

        System.out.println("  " + Arrays.toString(vertexes.toArray()));

        for (int i = 0; i < dis.length; i++) {
            System.out.print(vertexes.get(i) + " ");
            System.out.println(Arrays.toString(dis[i]));
        }

        System.out.println();

        for (String[] p : pre) {
            System.out.println(Arrays.toString(p));
        }
    }

}
