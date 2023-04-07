package com.example.datastructure.graph;

import java.util.Arrays;
import java.util.List;

/**
 * className DijkstraAlgorithm
 * packageName com.example.datastructure.graph
 * description
 *
 * @author CCC
 * @date 2021/1/15 23:16
 */
public class DijkstraAlgorithm {

    static final int N = 65535;

    public static void main(String[] args) {

        List<String> vertexes = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        int[][] matrix = new int[vertexes.size()][vertexes.size()];
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertexes, matrix);

        new DijkstraAlgorithm(graph).dijkstra(6);
    }

    private final Graph graph;

    private final List<String> vertexes;

    private final int[][] edges;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
        this.vertexes = graph.getVertexes();;
        this.edges = graph.getEdges();
    }

    /**
     * 迪杰斯特拉算法求指定顶点到其他顶点的最短路径
     * @param index 指定顶点索引
     */
    public void dijkstra(int index) {

        VisitedVertex visitedVertex = new VisitedVertex(vertexes.size(), index);

        update(visitedVertex, index);

        for (int i = 1; i < vertexes.size(); i++) {
            index = nextVertex(visitedVertex);
            update(visitedVertex, index);
        }
    }

    /**
     * 更新指定顶点的已访问记录
     * @param visitedVertex visitedVertex
     * @param index 顶点索引
     */
    private void update(VisitedVertex visitedVertex, int index) {

        for (int i = 0; i < edges[index].length; i++) {

            // 顶点已访问则直接跳过
            if (visitedVertex.visited[i] == 1) {
                continue;
            }

            // 指定顶点到index顶点的距离 + index顶点到i顶点的距离
            int len = visitedVertex.dis[index] + edges[index][i];
            // 已记录的指定顶点到i顶点的最小距离
            int dis = visitedVertex.dis[i];
            if (len < dis) {
                visitedVertex.dis[i] = len;
                // i顶点的前驱顶点为index顶点
                visitedVertex.preVertex[i] = index;
            }
        }

    }

    /**
     * 查找与指定顶点距离最短的顶点
     * @param visitedVertex visitedVertex
     * @return 顶点索引
     */
    private int nextVertex(VisitedVertex visitedVertex) {

        int min = N;

        int index = 0;

        for (int i = 0; i < vertexes.size(); i++) {

            // 顶点已访问则直接跳过
            if (visitedVertex.visited[i] == 1) {
                continue;
            }

            int dis = visitedVertex.dis[i];
            if (dis != N && dis < min) {
                min = dis;
                index = i;
            }
        }

        // 将该顶点标记为已访问
        visitedVertex.visited[index] = 1;

        return index;
    }

    static class VisitedVertex {

        /**
         * 标识已访问过的顶点
         */
        int[] visited;

        /**
         * 记录出发顶点到其他所有顶点的距离
         */
        int[] dis;

        /**
         * 前驱顶点索引
         */
        int[] preVertex;

        public VisitedVertex(int length, int index) {
            super();
            this.visited = new int[length];
            this.dis = new int[length];
            this.preVertex = new int[length];
            // 标记自身已访问
            this.visited[index] = 1;
            // 初始化距离为最大值
            Arrays.fill(dis, N);
            // 到自身的距离为0
            this.dis[index] = 0;
        }

    }

}
