package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 12. 矩阵中的路径
 */
public class Exist {

    @Test
    public  void test() {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        boolean exist = exist(board, word);
        assert exist;
        board = new char[][]{{'a', 'b'}, {'c', 'd'}};
        word = "cdba";
        exist = exist(board, word);
        assert exist;
    }

    char[][] board;
    String word;

    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    boolean[][] visited;

    public boolean exist(char[][] board, String word) {

        this.board = board;
        this.word = word;
        this.visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean exist(int i, int j, int k) {

        // 字符错误
        if (board[i][j] != this.word.charAt(k)) {
            return false;
        }
        // 判断当前字符是否完整
        if (k == this.word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        boolean exist = false;
        // 依次遍历四个方向
        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            // 边界条件
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                if (!visited[x][y]) {
                    exist = exist(x, y, k + 1);
                    if (exist) {
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;

        return exist;
    }

}
