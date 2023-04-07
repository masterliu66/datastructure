package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 212. 单词搜索 II
 *
 * @author CCC
 * @date 2021/9/16 21:20
 */
public class FindWords {

    public static void main(String[] args) {

        char[][] board = {{'a','a','a','a'},{'a','a','a','a'},{'a','a','a','a'},{'a','a','a','a'}};

        String[] words = {"a","aa","aaa","aaaa"};

        System.out.println(new FindWords(board, words).findWords());
    }

    private char[][] board;

    private String[] words;

    private List<String> ans;

    private final int[][] directions = {{-1,0},{0,-1},{1,0},{0,1}};

    public FindWords(char[][] board, String[] words) {
        this.board = board;
        this.words = words;
    }

    public List<String> findWords() {
        return findWords(board, words);
    }

    public List<String> findWords(char[][] board, String[] words) {

        this.board = board;
        this.words = words;
        this.ans = new ArrayList<>();

        Trie root = buildTrie();

        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(root, visited, i, j);
            }
        }

        return ans;
    }

    private void dfs(Trie root, boolean[][] visited, int i, int j) {

        int index = board[i][j] - 'a';
        Trie trie = root.children[index];
        if (trie == null) {
            return;
        }

        visited[i][j] = true;

        boolean end = trie.end();
        if (end) {
            ans.add(trie.clearWord());
        }

        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
                dfs(trie, visited, x, y);
            }
        }

        visited[i][j] = false;
        if (end && trie.empty()) {
            root.remove(index);
        }
    }

    private Trie buildTrie() {

        Trie root = new Trie();

        for (String word : words) {
            Trie trie = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                int index = word.charAt(i) - 'a';
                if (trie.children[index] == null) {
                    trie.children[index] = new Trie();
                }
                trie = trie.children[index];
            }
            trie.word = word;
        }

        return root;
    }

    class Trie {

        private String word;

        Trie[] children;

        public Trie() {
            this.children = new Trie[26];
        }

        public boolean end() {
            return word != null;
        }

        public boolean empty() {
            for (Trie child : children) {
                if (child != null) {
                    return false;
                }
            }
            return true;
        }

        public String clearWord() {
            String word = this.word;
            this.word = null;
            return word;
        }

        public void remove(int index) {
            children[index] = null;
        }

    }

}
