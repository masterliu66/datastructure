package com.example.datastructure.graph;

import java.util.Arrays;
import java.util.List;

import static com.example.datastructure.graph.Graph.INF;

/**
 * className PrimAlgorithm
 * packageName com.example.datastructure.graph
 * description
 *
 * @author CCC
 * @date 2021/1/14 22:45
 */
public class PrimAlgorithm {

    public static void main(String[] args) {

        List<String> vertexes = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        int[][] weight = new int[][]{
                {INF, 5,   7,   INF, INF, INF, 2},
                {5,   INF, INF, 9,   INF, INF, 3},
                {7,   INF, INF, INF, 8,   INF, INF},
                {INF, 9,   INF, INF, INF, 4,   INF},
                {INF, INF, 8,   INF, INF, 5,   4},
                {INF, INF, INF, 4,   5,   INF, 6},
                {2,   3,   INF, INF, 4,   6,   INF}};

        Graph graph = new Graph(vertexes, weight);

        new PrimAlgorithm().prim(graph, 0);
    }

    /**
     * 普利姆算法求最小生成树
     * @param graph 图
     * @param index 开始访问的顶点索引
     */
    public void prim(Graph graph, int index) {

        List<String> vertexes = graph.getVertexes();

        int[][] edges = graph.getEdges();

        // 标记顶点是否被访问过
        int[] visited = new int[vertexes.size()];

        // 将开始访问的顶点标记为已访问过
        visited[index] = 1;

        // h1 和 h2 记录两个顶点的索引
        int h1 = -1;
        int h2 = -1;

        // 最小生成树有 vertexes - 1 条边
        for(int k = 1; k < vertexes.size(); k++) {

            int minWeight = Integer.MAX_VALUE;
            // i 表示已访问过的顶点索引
            for (int i = 0; i < edges.length; i++) {
                // j 表示未访问过的顶点索引
                for (int j = 0; j < edges[i].length; j++) {
                    if (visited[i] == 1 && visited[j] == 0) {
                        int weight = edges[i][j];
                        if (weight < minWeight) {
                            h1 = i;
                            h2 = j;
                            minWeight = weight;
                        }
                    }
                }
            }

            // 将顶点 h2 标记为已访问过
            visited[h2] = 1;
            System.out.printf("<%s, %s> : %d\n", vertexes.get(h1), vertexes.get(h2), minWeight);
        }

    }

}
