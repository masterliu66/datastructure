package com.example.datastructure.algorithm;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 马踏棋盘(骑士周游)算法
 * packageName com.example.datastructure.algorithm
 * description
 *
 * @author CCC
 * @date 2021/1/17 23:29
 */
public class HorseChessboard {

    /** 棋盘的列数 */
    public static final int X = 8;

    /** 棋盘的行数 */
    public static final int Y = 8;

    /** 标记任务是否完成 */
    private static boolean finished = false;

    public static void main(String[] args) {

        int row = 1;

        int col = 1;

        int[][] chessBoard = new int[Y][X];

        long start = System.currentTimeMillis();

        new HorseChessboard().traversalChessboard(chessBoard, row - 1, col - 1, 1);

        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");

        for (int[] rows : chessBoard) {
            System.out.println(Arrays.toString(rows));
        }
    }

    /**
     * 马踏棋盘(骑士周游)算法
     * @param chessBoard 棋盘
     * @param row 行
     * @param col 列
     * @param step 步数
     */
    private void traversalChessboard(int[][] chessBoard, int row, int col, int step) {

        // 标记当前位置已走过
        chessBoard[row][col] = step;

        List<Point> nextPoints = getNext(new Point(col, row));
        // 对nextPoints集合按Point对象的下一步的位置数目进行非递减排序
        nextPoints.sort(Comparator.comparingInt(p -> getNext(p).size()));
        while (!nextPoints.isEmpty()) {
            Point point = nextPoints.remove(0);
            // 已访问过的位置直接跳过
            if (chessBoard[point.y][point.x] != 0) {
                continue;
            }
            traversalChessboard(chessBoard, point.y, point.x, step + 1);
        }

        if (step < X * Y && !finished) {
            // 将当前位置重置为未访问过
            chessBoard[row][col] = 0;
        } else {
            finished = true;
        }
    }

    /**
     * 根据马的当前位置获取下一步可以走的位置集合
     * @param point 当前位置
     * @return List<Point>
     */
    private List<Point> getNext(Point point) {

        List<Point> nextPoints = new ArrayList<>(8);

        int x;
        int y;

        /*
         * 马在棋盘最大可走的位置为8个
         * 0 0 0 0 0 0 0 0
         * 0 0 6 0 7 0 0 0
         * 0 5 0 0 0 8 0 0
         * 0 0 0 M 0 0 0 0
         * 0 1 0 0 0 4 0 0
         * 0 0 2 0 3 0 0 0
         * 0 0 0 0 0 0 0 0
         * 0 0 0 0 0 0 0 0
         * */
        // 位置5
        if ((x = point.x - 2) >= 0 && (y = point.y - 1) >= 0) {
            nextPoints.add(new Point(x, y));
        }
        // 位置6
        if ((x = point.x - 1) >= 0 && (y = point.y - 2) >= 0) {
            nextPoints.add(new Point(x, y));
        }
        // 位置7
        if ((x = point.x + 1) < X && (y = point.y - 2) >= 0) {
            nextPoints.add(new Point(x, y));
        }
        // 位置8
         if ((x = point.x + 2) < X && (y = point.y - 1) >= 0) {
            nextPoints.add(new Point(x, y));
        }
        // 位置4
        if ((x = point.x + 2) < X && (y = point.y + 1) < Y) {
            nextPoints.add(new Point(x, y));
        }
        // 位置3
        if ((x = point.x + 1) < X && (y = point.y + 2) < Y) {
            nextPoints.add(new Point(x, y));
        }
        // 位置2
        if ((x = point.x - 1) >= 0 && (y = point.y + 2) < Y) {
            nextPoints.add(new Point(x, y));
        }
        // 位置1
        if ((x = point.x - 2) >= 0 && (y = point.y + 1) < Y) {
            nextPoints.add(new Point(x, y));
        }

        return nextPoints;
    }

}
