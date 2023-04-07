package com.example.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * className Solution
 * packageName com.example.leetcode
 * description
 *
 * @author CCC
 * @date 2021/4/11 22:38
 */
public class Solution {

    int ans;

    @Test
    public void nthUglyNumber() {
        System.out.println(nthUglyNumber2(10));
    }

    @Test
    public void rob() {

        int[] nums = {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72};

        System.out.println(rob(nums));
    }

    @Test
    public void numDecodings() {

        String s = "10";

        System.out.println(numDecodings(s));
    }

    @Test
    public void largestDivisibleSubset() {

        int[] nums = {5,9,18,54,108,540,90,180,360,720};

        System.out.println(largestDivisibleSubset(nums));
    }

    @Test
    public void combinationSum4() {

        int[] nums = {1,2,3};
        int target = 4;

        System.out.println(combinationSum4(nums, target));
    }

    @Test
    public void shipWithinDays() {

        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int D = 5;
//        int[] weights = {488,247,151,268,358,270,366,2,85,49,209,37,353,17,287,385,421,467,32,201,398,27,108,291,435,447};
//        int D = 26;

        System.out.println(shipWithinDays(weights, D));
    }

    @Test
    public void getFolderNames() {

//        String[] names = {"wano","wano","wano","wano"};
//        String[] names = {"onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"};
//        String[] names = {"kaido","kaido(1)","kaido","kaido(1)"};
        String[] names = {"kaido","kaido(1)","kaido","kaido(1)","kaido(2)"};

        System.out.println(Arrays.toString(getFolderNames(names)));
    }

    @Test
    public void decode() {

        int[] encoded = {3,1};
//        int[] encoded = {6,5,4,6};

        System.out.println(Arrays.toString(decode(encoded)));
    }

    @Test
    public void findLengthOfShortestSubarray() {

//        int[] arr = {1,2,3,10,4,2,3,5};
//        int[] arr = {1,2,3,10,0,7,8,9};
        int[] arr = {1,5,5,5,10,3,5,5,5,20};

        System.out.println(findLengthOfShortestSubarray(arr));
    }

    @Test
    public void reverseParentheses() {

        String s = "a(bcdefghijkl(mno)p)q";

        System.out.println(reverseParentheses(s));
    }

    @Test
    public void lastStoneWeightII() {

        int[] stones = {2,7,4,1,8,1};

        System.out.println(lastStoneWeightII(stones));
        System.out.println(lastStoneWeightIIDp(stones));
    }

    @Test
    public void stoneGame() {

        int[] piles = {5,3,4,5};

        System.out.println(stoneGame(piles));
    }

    /**
     * 877. 石子游戏
     */
    public boolean stoneGame(int[] piles) {

        int n = piles.length;

        // dp[i][j] 表示[i,j]区间当前玩家与另一个玩家的石子数量之差的最大值
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] > 0;
    }

    @Test
    public void isNumber() {

        String s = "..";

        System.out.println(isNumber(s));
    }

    @Test
    public void numMagicSquaresInside() {

        int[][] grid = {{3,2,9,2,7},
                        {6,1,8,4,2},
                        {7,5,3,2,7},
                        {2,9,4,9,6},
                        {4,3,8,2,5}};

        System.out.println(numMagicSquaresInside(grid));
    }

    @Test
    public void readBinaryWatch() {

        int turnedOn = 1;

        System.out.println(readBinaryWatch(turnedOn));
    }

    @Test
    public void maximumScore() {

        int[] nums = {-5,-3,-3,-2,7,1};
        int[] multipliers = {-10,-5,3,4,6};

        System.out.println(maximumScore(nums, multipliers));
    }

    @Test
    public void snakesAndLadders() {

        int[][] board = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
        };

        System.out.println(snakesAndLadders(board));
    }

    @Test
    public void isMatch() {

        String s = "a", p = "ab*";

        System.out.println(isMatch(s, p));
    }

    @Test
    public void displayTable() {

        String[][] ordersArr = {
                {"David","3","Ceviche"},
                {"Corina","10","Beef Burrito"},
                {"David","3","Fried Chicken"},
                {"Carla","5","Water"},
                {"Carla","5","Ceviche"},
                {"Rous","3","Ceviche"}};

        List<List<String>> orders = new ArrayList<>();
        for (String[] orderArr : ordersArr) {
            List<String> order = new ArrayList<>();
            Collections.addAll(order, orderArr);
            orders.add(order);
        }

        System.out.println(displayTable(orders));
    }

    @Test
    public void numSubarraysWithSum() {

        int[] nums = {1,0,1,0,1};
        int goal = 2;

        System.out.println(numSubarraysWithSum(nums, goal));
    }

    @Test
    public void minAbsoluteSumDiff() {

        int[] nums1 = {1,10,4,4,2,7}, nums2 = {9,3,5,1,7,4};

        System.out.println(minAbsoluteSumDiff(nums1, nums2));
    }

    @Test
    public void groupAnagrams() {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(groupAnagrams(strs));
    }

    @Test
    public void maxFrequency() {

        int[] nums = {1,4,8,13};
        int k = 5;

        System.out.println(maxFrequency(nums, k));
    }

    @Test
    public void restoreArray() {

        int[][] adjacentPairs = {{2,1},{3,4},{3,2}};

        System.out.println(Arrays.toString(restoreArray(adjacentPairs)));
    }

    @Test
    public void distanceK() {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println(distanceK(root, root.left, 2));
    }

    @Test
    public void pathInZigZagTree() {

        System.out.println(pathInZigZagTree(14));
    }

    @Test
    public void verticalTraversal() {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(verticalTraversal(root));
    }

    @Test
    public void kWeakestRows() {

        int[][] mat =
               {{1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}};
        int k = 3;

        System.out.println(Arrays.toString(kWeakestRows(mat, k)));
    }

    @Test
    public void networkDelayTime() {

        int[][] times = {{3, 5, 78},
                        {2, 1, 1},
                        {1, 3, 0},
                        {4, 3, 59},
                        {5, 3, 85},
                        {5, 2, 22},
                        {2, 4, 23},
                        {1, 4, 43},
                        {4, 5, 75},
                        {5, 1, 15},
                        {1, 5, 91},
                        {4, 1, 16},
                        {3, 2, 98},
                        {3, 4, 22},
                        {5, 4, 31},
                        {1, 2, 0},
                        {2, 5, 4},
                        {4, 2, 51},
                        {3, 1, 36},
                        {2, 3, 59}};
        int n = 5, k = 5;

        System.out.println(networkDelayTime(times, n, k));
    }

    @Test
    public void eventualSafeNodes() {

        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};

        System.out.println(eventualSafeNodes(graph));
        System.out.println(eventualSafeNodesDfs(graph));
    }

    @Test
    public void nthSuperUglyNumber() {

        int n = 12;
        int[] primes = {2,7,13,19};

        System.out.println(nthSuperUglyNumber(n, primes));
        System.out.println(nthSuperUglyNumber2(n, primes));
    }

    @Test
    public void longestPalindromeSubseq() {

        String s = "nwlrbbmqbh";

        System.out.println(longestPalindromeSubseq(s));
        System.out.println(longestPalindromeSubseq2(s));
    }

    @Test
    public void unhappyFriends() {

        int n = 4;
        int[][] preferences = {{1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}};
        int[][] pairs = {{1, 3}, {0, 2}};

        System.out.println(unhappyFriends(n, preferences, pairs));
    }

    @Test
    public void findPaths() {

        int m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0;
//        int m = 45, n = 35, maxMove = 47, startRow = 20, startColumn = 31;

        System.out.println(findPaths(m, n, maxMove, startRow, startColumn));
    }

    @Test
    public void countArrangement() {
        int n = 12;
        System.out.println(countArrangement(n));
        System.out.println(countArrangement2(n));
    }

    @Test
    public void reverseVowels() {

        String s = "leetcode";

        System.out.println(reverseVowels(s));
    }

    @Test
    public void compress() {

        char[] chars = {'a','a','b','b','c','c','c'};

        System.out.println(compress(chars));
    }

    @Test
    public void findCheapestPrice() {

        int n = 5;
        int[][] edges = {{0,1,100},{0,2,100},{0,3,10},{1,2,100},{1,4,10},{2,1,10},{2,3,100},{2,4,100},{3,2,10},{3,4,100}};
        int src = 0, dst = 4, k = 3;
        System.out.println(findCheapestPrice(n, edges, src, dst, k));
        System.out.println(findCheapestPriceDfs(n, edges, src, dst, k));
    }

    @Test
    public void compareVersion() {

        String version1 = "7.5.2.4", version2 = "7.5.3";

        System.out.println(compareVersion(version1, version2));
    }

    @Test
    public void smallestK() {

        int[] arr = {1,3,5,7,2,4,6,8};
        int k = 4;

        System.out.println(Arrays.toString(smallestK(arr, k)));
    }

    @Test
    public void findMaximizedCapital() {

        int k = 3, w = 0;
        int[] profits = {1,2,3};
        int[] capital = {0,0,0};

        System.out.println(findMaximizedCapital(k, w, profits, capital));
    }

    /**
     * 502. IPO
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int n = profits.length;

        boolean speedUp = true;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {capital[i], profits[i]};
            if (speedUp && w < capital[i]) {
                speedUp = false;
            }
        }

        if (speedUp) {
            Arrays.sort(profits);
            for (int i = 0; i < Math.min(k, n); i++) {
                w += profits[n - 1 - i];
            }
            return w;
        }

        Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));

        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);

        int cursor = 0;
        for (int i = 0; i < k; i++) {
            while (cursor < n && arr[cursor][0] <= w) {
                queue.offer(arr[cursor][1]);
                cursor++;
            }
            if (queue.isEmpty()) {
                return w;
            }
            w += queue.poll();
        }

        return w;
    }

    @Test
    public void fullJustify() {

        String[] words = {"Science","is","what","we","understand","well","enough","to","explain", "to","a","computer.","Art","is","everything","else","we","do"};
        int maxWidth = 20;
        List<String> list = fullJustify(words, maxWidth);
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void checkValidString() {

        String s = "(*";

        System.out.println(checkValidString(s));
    }

    @Test
    public void numberOfBoomerangs() {

        int[][] points = {{0,0},{1,0},{1,1},{2,0}};

        System.out.println(numberOfBoomerangs(points));
    }

    @Test
    public void findPeakElement() {

        int[] nums = {1,2,1,3,5,6,4};

        System.out.println(findPeakElement(nums));
    }

    @Test
    public void isValidSudoku() {

        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println(isValidSudoku(board));
    }

    @Test
    public void minDistance() {

        String word1 = "neumonouo", word2 = "un";

        System.out.println(minDistance(word1, word2));
    }

    @Test
    public void pathSum() {

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        int targetSum = 8;

        System.out.println(pathSum(root, targetSum));
    }

    @Test
    public void findRepeatedDnaSequences() {

        String s = "TAAAAAAAAAAA";

        System.out.println(findRepeatedDnaSequences(s));
    }

    @Test
    public void arrangeCoins() {

        int n = 1804289383;

        System.out.println(arrangeCoins(n));
    }

    /**
     * 441. 排列硬币
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {

        int left = 1, right = 65535;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            long x = mid * (mid + 1L) >> 1;
            if (x == n) {
                return mid;
            } else if (x < n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public List<String> findRepeatedDnaSequences(String s) {

        int n = s.length();

        if (n < 10) {
            return new ArrayList<>();
        }

        Set<String> ans = new HashSet<>();
        Set<String> set = new HashSet<>();
        for (int i = 10; i <= n; i++) {
            String dna = s.substring(i - 10, i);
            if (set.contains(dna)) {
                ans.add(dna);
            } else {
                set.add(dna);
            }
        }

        return new ArrayList<>(ans);
    }

    /**
     * 437. 路径总和 III
     */
    public int pathSum(TreeNode root, int targetSum) {

        Map<Long, Integer> map = new HashMap<>();

        map.put(0L, 1);

        return dfs(root, map, targetSum, 0);
    }

    private int dfs(TreeNode node, Map<Long, Integer> map, int targetSum, long preSum) {

        if (node == null) {
            return 0;
        }

        preSum += node.val;

        int res = map.getOrDefault(preSum - targetSum, 0);

        map.put(preSum, map.getOrDefault(preSum, 0) + 1);

        res += dfs(node.left, map, targetSum, preSum);
        res += dfs(node.right, map, targetSum, preSum);

        map.put(preSum, map.get(preSum) - 1);

        return res;
    }

    /**
     * 583. 两个字符串的删除操作
     */
    public int minDistance(String word1, String word2) {

        int n1 = word1.length();
        int n2 = word2.length();

        // dp[i][j] 表示word1以i为结尾, word2以j为结尾时相同子序列的最大长度
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n2; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return n1 + n2 - dp[n1][n2] * 2;
    }

    /**
     * 36. 有效的数独
     */
    public boolean isValidSudoku(char[][] board) {

        int[] rowMask = new int[9];
        int[] colMask = new int[9];
        int[] matrixMask = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int val = c - '0';
                if (((rowMask[i] >> val) & 1) == 1) {
                    return false;
                }
                if (((colMask[j] >> val) & 1) == 1) {
                    return false;
                }
                int matrixI = i / 3;
                int matrixJ = j / 3;
                int matrixIndex = matrixI * 3 + matrixJ;
                if (((matrixMask[matrixIndex] >> val) & 1) == 1) {
                    return false;
                }
                rowMask[i] |= 1 << val;
                colMask[j] |= 1 << val;
                matrixMask[matrixIndex] |= 1 << val;
            }
        }

        return true;
    }

    /**
     * 162. 寻找峰值
     */
    public int findPeakElement(int[] nums) {

        int n = nums.length;

        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 447. 回旋镖的数量
     */
    public int numberOfBoomerangs(int[][] points) {

        int n = points.length;

        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int x2 = points[j][0];
                int y2 = points[j][1];
                int distance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                Integer count = map.getOrDefault(distance, 0);
                map.put(distance, count + 1);
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                if (count < 2) {
                    continue;
                }
                // A(n,2)
                int permutation = count * (count - 1);
                total += permutation;
            }
            map.clear();
        }

        return total;
    }

    /**
     * 678. 有效的括号字符串
     */
    public boolean checkValidString(String s) {

        int n = s.length();

        Deque<Integer> leftParenthesisStack = new LinkedList<>();
        Deque<Integer> startStack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (!leftParenthesisStack.isEmpty()) {
                    leftParenthesisStack.pop();
                    continue;
                }
                if (!startStack.isEmpty()) {
                    startStack.pop();
                    continue;
                }
                return false;
            } else if (c == '(') {
                leftParenthesisStack.push(i);
            } else {
                startStack.push(i);
            }
        }

        while (!leftParenthesisStack.isEmpty()) {
            Integer index = leftParenthesisStack.pop();
            if (startStack.isEmpty() || startStack.peek() < index) {
                return false;
            }
            startStack.pop();
        }

        return true;
    }

    /**
     * 1894. 找到需要补充粉笔的学生编号
     */
    public int chalkReplacer(int[] chalk, int k) {

        if (chalk[0] > k) {
            return 0;
        }

        int n = chalk.length;

        for (int i = 1; i < n; ++i) {
            chalk[i] += chalk[i - 1];
            if (chalk[i] > k) {
                return i;
            }
        }

        k %= chalk[n - 1];

        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (k >= chalk[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 68. 文本左右对齐
     */
    public List<String> fullJustify(String[] words, int maxWidth) {

        int n = words.length;

        List<String> ans = new ArrayList<>();

        int start = 0;
        int len = words[0].length();
        for (int i = 1; i < n; i++) {
            String word = words[i];
            if (word.length() + len + i - start <= maxWidth) {
                len += word.length();
                continue;
            }
            ans.add(mergeWords(words, maxWidth - len, start, i - 1));
            len = word.length();
            start = i;
        }

        StringBuilder builder = new StringBuilder(words[start]);
        for (int i = start + 1; i < n; i++) {
            builder.append(" ");
            builder.append(words[i]);
        }

        for (int k = 0; k < (maxWidth - len - n + 1 + start); k++) {
            builder.append(" ");
        }

        ans.add(builder.toString());

        return ans;
    }

    private String mergeWords(String[] words, int blankCount, int start, int end) {
        StringBuilder builder = new StringBuilder(words[start]);
        if (start == end) {
            for (int k = 0; k < blankCount; k++) {
                builder.append(" ");
            }
            return builder.toString();
        }

        int spaceCount = end - start;
        int ceil = (blankCount + spaceCount - 1) / spaceCount;
        int floor = blankCount / spaceCount;
        for (int j = start + 1; j <= end; j++) {
            int spaceBlankCount = ceil;
            if (ceil != floor && (j - start) * ceil + (end - j) * floor > blankCount) {
                spaceBlankCount = floor;
            }
            for (int k = 0; k < spaceBlankCount; k++) {
                builder.append(" ");
            }
            builder.append(words[j]);
        }

        return builder.toString();
    }

    /**
     * 面试题 17.14. 最小K个数
     */
    public int[] smallestK(int[] arr, int k) {

        if (k == 0) {
            return new int[] {};
        }

        quickSort(arr, 0, arr.length - 1, k);

        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);

        return ans;
    }

    private void quickSort(int[] arr, int left, int right, int k) {

        int pivot = arr[left];

        int i = left, j = right;
        while (i < j) {

            while (i < j && pivot <= arr[j]) {
                j--;
            }

            while (i < j && pivot >= arr[i]) {
                i++;
            }

            swap(arr, i, j);
        }

        swap(arr, left, i);

        if (i == k - 1) {
            return;
        }

        if (i < k) {
            quickSort(arr, i + 1, right, k);
        } else {
            quickSort(arr, left, i - 1, k);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 165. 比较版本号
     */
    public int compareVersion(String version1, String version2) {

        String[] v1 = version1.split("\\.");

        String[] v2 = version2.split("\\.");

        int n = Math.max(v1.length, v2.length);

        for (int i = 0; i < n; i++) {
            int x = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int y = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (x < y) {
                return -1;
            } else if (x > y) {
                return 1;
            }
        }

        return 0;
    }

    /**
     * 787. K 站中转内最便宜的航班
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            map.computeIfAbsent(flight[0], x -> new LinkedList<>()).add(flight);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {src, 0});

        // d[i]表示各顶点与src的最短距离
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        int step = 0;
        while (step <= k && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                if (!map.containsKey(p[0])) {
                    continue;
                }
                for (int[] flight : map.get(p[0])) {
                    int price = p[1] + flight[2];
                    if (price >= d[flight[1]] || price >= d[dst]) {
                        continue;
                    }
                    d[flight[1]] = price;
                    if (flight[1] == dst) {
                        continue;
                    }
                    queue.offer(new int[] {flight[1], price});
                }
            }
            step++;
        }

        return d[dst] == Integer.MAX_VALUE ? -1 : d[dst];
    }

    Map<Integer, List<int[]>> map;
    int dst;

    public int findCheapestPriceDfs(int n, int[][] flights, int src, int dst, int k) {

        this.dst = dst;

        this.map = new HashMap<>();
        for (int[] flight : flights) {
            map.computeIfAbsent(flight[0], x -> new LinkedList<>()).add(flight);
        }

        int[][] memo = new int[n][k + 2];

        int price = dfs(src, k + 1, memo);

        return price == Integer.MAX_VALUE ? -1 : price;
    }

    private int dfs(int src, int k, int[][] memo) {

        if (k < 0) {
            return Integer.MAX_VALUE;
        }

        if (src == dst) {
            return 0;
        }

        if (memo[src][k] != 0) {
            return memo[src][k];
        }

        List<int[]> flights = map.get(src);
        if (flights == null) {
            return Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;
        for (int[] flight : flights) {
            int price = dfs(flight[1], k - 1, memo);
            if (price != Integer.MAX_VALUE) {
                min = Math.min(min, price + flight[2]);
            }
        }

        memo[src][k] = min;

        return min;
    }

    /**
     * 443. 压缩字符串
     */
    public int compress(char[] chars) {

        int n = chars.length;

        int slow = 0;
        int count = 1;
        for (int fast = 1; fast <= n; fast++) {
            if (fast == n || chars[fast] != chars[fast - 1]) {
                chars[slow++] = chars[fast - 1];
                if (count == 1) {
                    continue;
                }
                LinkedList<Integer> digits = new LinkedList<>();
                while (count > 0) {
                    digits.addFirst(count % 10);
                    count /= 10;
                }
                for (Integer digit : digits) {
                    chars[slow++] = (char) ('0' + digit);
                }
                count = 1;
            } else {
                count++;
            }

        }

        return slow;
    }

    /**
     * 345. 反转字符串中的元音字母
     */
    public String reverseVowels(String s) {

        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        Set<Character> set = new HashSet<>();
        for (char vowel : vowels) {
            set.add(vowel);
        }

        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {

            while (left < right && !set.contains(chars[left])) {
                left++;
            }

            while (left < right && !set.contains(chars[right])) {
                right--;
            }

            if (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
        }

        return new String(chars);
    }

    /**
     * 526. 优美的排列
     */
    public int countArrangement(int n) {

        // dp[i]表示状态为i时的方案数
        int[] dp = new int[1 << n];
        dp[0] = 1;
        for (int mask = 1; mask < 1 << n; mask++) {
            int num = Integer.bitCount(mask);
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1 && (num % (i + 1) == 0 || (i + 1) % num == 0)) {
                    dp[mask] += dp[mask ^ (1 << i)];
                }
            }
        }

        return dp[(1 << n) - 1];
    }

    public int countArrangement2(int n) {

        boolean[] visited = new boolean[n];

        countArrangement(1, n, visited);

        return ans;
    }

    private void countArrangement(int step, int n, boolean[] visited) {

        if (step == n + 1) {
            ans++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i - 1]) {
                visited[i - 1] = true;
                if (step % i == 0 || i % step == 0) {
                    countArrangement(step + 1, n, visited);
                }
                visited[i - 1] = false;
            }
        }

    }

    /**
     * 576. 出界的路径数
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {

        if (maxMove == 0) {
            return 0;
        }

        // dp[i][j][k] 表示移动k次时从起点到达[i,j]的路径数量
        int[][][] dp = new int[m][n][maxMove + 1];
        dp[startRow][startColumn][0] = 1;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int ans = 0;
        for (int k = 0; k < maxMove; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int count = dp[i][j][k];
                    if (count > 0) {
                        for (int[] direction : directions) {
                            int x = i + direction[0];
                            int y = j + direction[1];
                            if (x >= 0 && x < m && y >= 0 && y < n) {
                                dp[x][y][k + 1] = (dp[x][y][k + 1] + count) % 1000000007;
                            } else {
                                ans = (ans + count) % 1000000007;
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }

    /**
     * 1583. 统计不开心的朋友
     */
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {

        int[][] priorities = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                priorities[i][preferences[i][j]] = n - 1 - j;
            }
        }

        boolean[] unhappyFriends = new boolean[n];

        for (int i = 0; i < pairs.length; i++) {
            int x1 = pairs[i][0];
            int y1 = pairs[i][1];
            for (int j = i + 1; j < pairs.length; j++) {
                int x2 = pairs[j][0];
                int y2 = pairs[j][1];
                flagUnhappyFriends(priorities, unhappyFriends, x1, y1, x2, y2);
            }
        }

        int ans = 0;
        for (boolean unhappyFriend : unhappyFriends) {
            if (unhappyFriend) {
                ans++;
            }
        }

        return ans;
    }

    private void flagUnhappyFriends(int[][] priorities, boolean[] unhappyFriends, int x1, int y1, int x2, int y2) {

        int priorityX1Y1 = priorities[x1][y1];
        int priorityX1X2 = priorities[x1][x2];
        int priorityX1Y2 = priorities[x1][y2];

        int priorityY1X1 = priorities[y1][x1];
        int priorityY1X2 = priorities[y1][x2];
        int priorityY1Y2 = priorities[y1][y2];

        int priorityX2X1 = priorities[x2][x1];
        int priorityX2Y1 = priorities[x2][y1];
        int priorityX2Y2 = priorities[x2][y2];

        int priorityY2X1 = priorities[y2][x1];
        int priorityY2X2 = priorities[y2][x2];
        int priorityY2Y1 = priorities[y2][y1];

        // X1 X2
        if (priorityX1X2 > priorityX1Y1 && priorityX2X1 > priorityX2Y2) {
            unhappyFriends[x1] = true;
            unhappyFriends[x2] = true;
        }

        // X1 Y2
        if (priorityX1Y2 > priorityX1Y1 && priorityY2X1 > priorityY2X2) {
            unhappyFriends[x1] = true;
            unhappyFriends[y2] = true;
        }

        // Y1 X2
        if (priorityY1X2 > priorityY1X1 && priorityX2Y1 > priorityX2Y2) {
            unhappyFriends[y1] = true;
            unhappyFriends[x2] = true;
        }

        // Y1 Y2
        if (priorityY1Y2 > priorityY1X1 && priorityY2Y1 > priorityY2X2) {
            unhappyFriends[y1] = true;
            unhappyFriends[y2] = true;
        }
    }

    /**
     * 516. 最长回文子序列
     */
    public int longestPalindromeSubseq(String s) {

        int n = s.length();

        // dp[i][j] 表示区间[i,j]的最长回文子序列
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                if (c == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq2(String s) {

        int n = s.length();

        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(s.charAt(i), x -> new LinkedList<>()).add(i);
        }

        // g[len]为以回文子序列左边界长度为len的回文右边界的最大下标, g[len]是递减序列
        int[] g = new int[n];

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            List<Integer> indexes = map.get(c);
            for (Integer j : indexes) {
                if (j <= i) {
                    continue;
                }
                int left = 0, right = i;
                while (left < right) {
                    int mid = left + right + 1 >> 1;
                    if (j < g[mid]) {
                        left = mid;
                    } else {
                        right = mid - 1;
                    }
                }
                if (left == 0 || g[left] > j) {
                    g[left + 1] = Math.max(g[left + 1], j);
                    if (left + 1 > max) {
                        max = left + 1;
                        maxIndex = i;
                    }
                }
            }
        }

        // 没有找到回文子序列
        if (max == 0) {
            return 1;
        }

        return max * 2 + (maxIndex + 1 < g[max] ? 1 : 0);
    }

    /**
     * 313. 超级丑数
     */
    public int nthSuperUglyNumber(int n, int[] primes) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        Set<Integer> set = new HashSet<>();
        set.add(1);
        queue.offer(1);

        int ugly = 0;
        for (int i = 0; i < n; i++) {
            ugly = queue.poll();
            for (int prime : primes) {
                if (ugly > Math.ceil(Integer.MAX_VALUE / (double) prime)) {
                    break;
                }
                int next = ugly * prime;
                if (set.add(next)) {
                    queue.offer(next);
                }
            }

        }

        return ugly;
    }

    public int nthSuperUglyNumber2(int n, int[] primes) {

        int len = primes.length;

        // dp[i]表示第i个丑数
        int[] dp = new int[n + 1];
        dp[1] = 1;

        // 指针px(x ∈ primes) 表示满足dp[j] * x > dp[i - 1] 最小的下标j
        int[] p = new int[len];
        Arrays.fill(p, 1);
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < p.length; j++) {
                min = Math.min(min, dp[p[j]] * primes[j]);
            }
            for (int j = 0; j < p.length; j++) {
                if (dp[p[j]] * primes[j] == min) {
                    p[j]++;
                }
            }
            dp[i] = min;
        }

        return dp[n];
    }

    /**
     * 802. 找到最终的安全状态
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;

        // outDegrees[i] 表示顶点i的出度
        int[] outDegrees = new int[n];

        List<List<Integer>> reverseGraph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            reverseGraph.add(new LinkedList<>());
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 建立反向索引
            for (int j : graph[i]) {
                reverseGraph.get(j).add(i);
            }
            outDegrees[i] = graph[i].length;
            // 将出度为0的顶点入队列
            if (outDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            // 将与出度为0的顶点相连的顶点的出度减一
            for (Integer i : reverseGraph.get(vertex)) {
                outDegrees[i]--;
                if (outDegrees[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (outDegrees[i] == 0) {
                ans.add(i);
            }
        }

        return ans;
    }

    public List<Integer> eventualSafeNodesDfs(int[][] graph) {

        int n = graph.length;

        /*
         * 0: 顶点未访问
         * 1: 顶点处于递归中或成环
         * 2: 安全顶点
         */
        int[] color = new int[n];

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(graph, color, i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    private boolean dfs(int[][] graph, int[] color, int x) {

        if (color[x] > 0) {
            return color[x] == 2;
        }

        color[x] = 1;

        for (int y : graph[x]) {
            if (!dfs(graph, color, y)) {
                return false;
            }
        }

        color[x] = 2;

        return true;
    }

    /**
     * 743. 网络延迟时间
     */
    public int networkDelayTime(int[][] times, int n, int k) {

        final int INF = Integer.MAX_VALUE / 2;

        int[][] edges = new int[n][n];
        for (int[] edge : edges) {
            Arrays.fill(edge, INF);
        }

        for (int[] time : times) {
            edges[time[0] - 1][time[1] - 1] = time[2];
        }

        boolean[] visited = new boolean[n];

        // dist[i] 表示起点到顶点i的最短距离
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;

        for (int m = 0; m < n; m++) {

            int i = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (i == -1 || dist[j] < dist[i])) {
                    i = j;
                }
            }
            visited[i] = true;
            for (int j = 0; j < n; j++) {
                dist[j] = Math.min(dist[j], dist[i] + edges[i][j]);
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();

        return ans == INF ? -1 : ans;
    }

    /**
     * 1337. 矩阵中战斗力最弱的 K 行
     */
    public int[] kWeakestRows(int[][] mat, int k) {

        PriorityQueue<int[]> queue = new PriorityQueue<>((tuple1, tuple2) -> {
            if (tuple1[0] != tuple2[0]) {
                return tuple1[0] - tuple2[0];
            }
            return tuple1[1] - tuple2[1];
        });

        for (int i = 0; i < mat.length; i++) {
            int[] row = mat[i];
            int left = 0, right = row.length - 1;
            while (left < right) {
                int mid = left + right + 1 >> 1;
                if (row[mid] == 0) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            int sum = row[left] == 1 ? left + 1 : left;
            queue.offer(new int[] {sum, i});
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll()[1];
        }

        return ans;
    }

    PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((tuple1, tuple2) -> {
        if (tuple1[0] != tuple2[0]) {
            return tuple1[0] - tuple2[0];
        }
        if (tuple1[1] != tuple2[1]) {
            return tuple1[1] - tuple2[1];
        }
        return tuple1[2] - tuple2[2];
    });

    /**
     * 987. 二叉树的垂序遍历
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {

        dfs(root, 0, 0);

        List<List<Integer>> ans = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int[] tuple = priorityQueue.peek();
            while (!priorityQueue.isEmpty() && priorityQueue.peek()[0] == tuple[0]) {
                tmp.add(priorityQueue.poll()[2]);
            }
            ans.add(tmp);
        }

        return ans;
    }

    private void dfs(TreeNode root, int row, int col) {

        if (root == null) {
            return;
        }

        priorityQueue.offer(new int[] {col, row, root.val});

        dfs(root.left, row + 1, col - 1);
        dfs(root.right, row + 1, col + 1);
    }

    /**
     * 1104. 二叉树寻路
     */
    public List<Integer> pathInZigZagTree(int label) {

        LinkedList<Integer> ans = new LinkedList<>();

        int level = (int) (Math.log(label) / Math.log(2)) + 1;
        while (level > 0) {
            ans.addFirst(label);
            // 找到第level层与label对称位置的节点
            label = (1 << level - 1) + (1 << level) - 1 - label;
            label >>= 1;
            level--;
        }

        return ans;
    }

    /**
     * 863. 二叉树中所有距离为 K 的结点
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        List<Integer> ans = new ArrayList<>();
        if (k == 0) {
            ans.add(target.val);
            return ans;
        }

        Set<Integer> set = new HashSet<>();

        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, TreeNode> parents = new HashMap<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                parents.put(node.left.val, node);
            }
            if (node.right != null) {
                queue.offer(node.right);
                parents.put(node.right.val, node);
            }
        }

        TreeNode node = target;
        while (node != null) {
            queue.offer(node);
            int distance = k;
            while (!queue.isEmpty()) {
                if (distance == 0) {
                    break;
                }
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode p = queue.poll();
                    if (p.left != null && !set.contains(p.left.val)) {
                        queue.offer(p.left);
                    }
                    if (p.right != null && !set.contains(p.right.val)) {
                        queue.offer(p.right);
                    }
                }
                distance--;
            }

            while (!queue.isEmpty()) {
                ans.add(queue.poll().val);
            }

            set.add(node.val);
            node = parents.get(node.val);
            k--;
        }

        return ans;
    }

    /**
     * 1743. 从相邻元素对还原数组
     */
    public int[] restoreArray(int[][] adjacentPairs) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] adjacentPair : adjacentPairs) {
            int num1 = adjacentPair[0];
            int num2 = adjacentPair[1];
            map.computeIfAbsent(num1, key -> new LinkedList<>()).add(num2);
            map.computeIfAbsent(num2, key -> new LinkedList<>()).add(num1);
        }

        Integer firstNum = map.entrySet().stream()
                .filter(entry -> entry.getValue().size() == 1)
                .findAny().orElse(null).getKey();

        int[] nums = new int[adjacentPairs.length + 1];
        nums[0] = firstNum;
        nums[1] = map.get(firstNum).get(0);

        for (int i = 2; i < nums.length; i++) {
            for (Integer num : map.get(nums[i - 1])) {
                if (num != nums[i - 2]) {
                    nums[i] = num;
                    break;
                }
            }
        }

        return nums;
    }

    /**
     * 1838. 最高频元素的频数
     */
    public int maxFrequency(int[] nums, int k) {

        Arrays.sort(nums);

        int n = nums.length;

        int left = 0;
        int right = 0;
        long sum = 0;
        while (right < n) {
            sum += nums[right];
            if (sum + k < (long)(right - left + 1) * nums[right]) {
                sum -= nums[left];
                left++;
            }
            right++;
        }

        return right - left;
    }

    /**
     * 面试题 10.02. 变位词组
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] words = new int[26];
            for (int i = 0; i < str.length(); i++) {
                words[str.charAt(i) - 'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for (int word : words) {
                builder.append(word);
            }
            map.computeIfAbsent(builder.toString(), x -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 1818. 绝对差值和
     */
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {

        int mod = 1000000007;

        int n = nums1.length;

        int[] nums1Copy = Arrays.copyOf(nums1, n);
        Arrays.sort(nums1Copy);

        int absoluteSum = 0;
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            int num1 = nums1[i];
            int num2 = nums2[i];
            if (num1 == num2) {
                continue;
            }
            int absolute = Math.abs(num1 - num2);
            // 二分查找nums1中大于等于num2的最小值
            int left = 0, right = n - 1;
            if (num2 > nums1Copy[right]) {
                left = n;
            } else {
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (nums1Copy[mid] < num2) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
            }

            if (left < n) {
                maxDiff = Math.max(maxDiff, absolute - (nums1Copy[left] - num2));
            }

            if (left > 0) {
                maxDiff = Math.max(maxDiff, absolute - (num2 - nums1Copy[left - 1]));
            }

            absoluteSum = (absoluteSum + absolute) % mod;
        }

        return (absoluteSum + mod - maxDiff) % mod;
    }

    /**
     * 930. 和相同的二元子数组
     */
    public int numSubarraysWithSum(int[] nums, int goal) {

        int n = nums.length;

        int left1 = 0, left2 = 0;
        int sum1 = 0, sum2 = 0;

        int ans = 0;
        for (int right = 0; right < n; right++) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }

            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            ans += left2 - left1;
        }

        return ans;
    }

    /**
     * 1418. 点菜展示表
     */
    public List<List<String>> displayTable(List<List<String>> orders) {

        TreeMap<String, Map<String, Integer>> map = new TreeMap<>(Comparator.comparingInt(Integer::parseInt));
        TreeSet<String> set = new TreeSet<>();
        for (List<String> order : orders) {
            String tableNum = order.get(1);
            String foodName = order.get(2);
            Map<String, Integer> foodMap = map.computeIfAbsent(tableNum, x -> new HashMap<>());
            Integer foodNums = foodMap.getOrDefault(foodName, 0);
            foodMap.put(foodName, ++foodNums);
            set.add(foodName);
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> first = new ArrayList<>();
        first.add("Table");
        first.addAll(set);
        ans.add(first);

        map.forEach((tableNum, foodMap) -> {
            List<String> row = new ArrayList<>();
            row.add(tableNum);
            for (int i = 1; i < first.size(); i++) {
                Integer foodNums = foodMap.getOrDefault(first.get(i), 0);
                row.add(String.valueOf(foodNums));
            }
            ans.add(row);
        });

        return ans;
    }

    /**
     * 剑指 Offer 19. 正则表达式匹配
     */
    public boolean isMatch(String s, String p) {

        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String s, String p, int sIndex, int pIndex) {

        if (sIndex == s.length() && pIndex == p.length()) {
            return true;
        }

        if (pIndex == p.length()) {
            return false;
        }

        char cp = p.charAt(pIndex);

        if (pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*') {
            for (int i = 0; i <= s.length() - sIndex; i++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < i; j++) {
                    builder.append(cp);
                }
                for (int j = pIndex + 2; j < p.length(); j++) {
                    builder.append(p.charAt(j));
                }
                if (isMatch(s, builder.toString(), sIndex, 0)) {
                    return true;
                }
            }
        } else {
           if (sIndex == s.length()) {
               return false;
           }
           char cs = s.charAt(sIndex);
           if (cp != '.' && cs != cp) {
               return false;
           }

           return isMatch(s, p, sIndex + 1, pIndex + 1);
        }

        return false;
    }

    /**
     * 909. 蛇梯棋
     */
    public int snakesAndLadders(int[][] board) {

        int n = board.length;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        Set<Integer> seen = new HashSet<>();
        seen.add(0);

        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer status = queue.poll();
                for (Integer nextStatus : getNextStatuses(board, status)) {
                    if (nextStatus == n * n - 1) {
                        return step;
                    }
                    if (seen.contains(nextStatus)) {
                        continue;
                    }
                    queue.offer(nextStatus);
                    seen.add(nextStatus);
                }
            }

        }

        return -1;
    }

    private int[] getRowAndCol(int status, int n) {

        int row = status / n;
        int col = status - row * n;
        if (row % 2 != 0) {
            col = n - 1 - col;
        }

        return new int[] {n - 1 - row, col};
    }

    private List<Integer> getNextStatuses(int[][] board, int status) {

        int n = board.length;

        List<Integer> statuses = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            if (status + i > n * n - 1) {
                break;
            }
            int[] rowAndCol = getRowAndCol(status + i, n);
            int row = rowAndCol[0];
            int col = rowAndCol[1];
            if (board[row][col] == -1) {
                statuses.add(status + i);
            } else {
                statuses.add(board[row][col] - 1);
            }
        }

        return statuses;
    }

    /**
     * 1770. 执行乘法运算的最大分数
     */
    public int maximumScore(int[] nums, int[] multipliers) {

        int n = nums.length;

        int m = multipliers.length;

        // dp[i] 表示区间nums[i, i + (n - m)]能够取得最大的分数
        int[] dp = new int[m];
        // 最后一步
        for (int i = 0; i < m; i++) {
            int j = i + (n - m);
            dp[i] = Math.max(nums[i] * multipliers[m - 1], nums[j] * multipliers[m - 1]);
        }

        // 自底向上进行遍历
        for (int len = 1; len < m; len++) {

            for (int i = 0; i < m - len; i++) {
                int j = i + (n - m) + len;
                dp[i] = Math.max(dp[i + 1] + nums[i] * multipliers[m - 1 - len], dp[i] + nums[j] * multipliers[m - 1 - len]);
            }

        }

        return dp[0];
    }

    /**
     * 401. 二进制手表
     */
    public List<String> readBinaryWatch(int turnedOn) {

        List<String> ans = new ArrayList<>();
        if (turnedOn >= 9) {
            return ans;
        }

        for (int i = 0; i < 1 << 10; i++) {

            if (Integer.bitCount(i) == turnedOn) {

                int hour = (i >> 6) & 0x0f;
                int minute = i & 0x3f;

                if (hour > 11 || minute > 59) {
                    continue;
                }

                ans.add(hour + ":" + (minute < 10 ? "0" : "") + minute);
            }

        }

        return ans;
    }

    /**
     * 840. 矩阵中的幻方
     */
    public int numMagicSquaresInside(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        if (n < 3 || m < 3) {
            return 0;
        }

        int ans = 0;
        for (int r = 0; r < n - 2; r++) {
            for (int c = 0; c < m - 2; c++) {
                if (grid[r+1][c+1] != 5) {
                    continue;
                }
                if (isNumMagic(grid[r][c], grid[r][c+1], grid[r][c+2],
                        grid[r+1][c], grid[r+1][c+1], grid[r+1][c+2],
                        grid[r+2][c], grid[r+2][c+1], grid[r+2][c+2])) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean isNumMagic(int... vals) {

        int[] count = new int[16];
        for (int v: vals) {
            count[v]++;
        }

        for (int v = 1; v <= 9; v++) {
            if (count[v] != 1) {
                return false;
            }
        }

        return (vals[0] + vals[1] + vals[2] == 15 &&
                vals[3] + vals[4] + vals[5] == 15 &&
                vals[6] + vals[7] + vals[8] == 15 &&
                vals[0] + vals[3] + vals[6] == 15 &&
                vals[1] + vals[4] + vals[7] == 15 &&
                vals[2] + vals[5] + vals[8] == 15 &&
                vals[0] + vals[4] + vals[8] == 15 &&
                vals[2] + vals[4] + vals[6] == 15);
    }

    /**
     * 65. 有效数字
     */
    public boolean isNumber(String s) {

        int len = s.length();

        if (len == 1 && ".".equals(s)) {
            return false;
        }

        int dotIndex = -1;

        int eIndex = -1;

        for (int i = 0; i < len; i++) {

            char c = s.charAt(i);

            if (isDigit(c)) {
                continue;
            }

            if (i == len - 1) {
                if (c != '.' || dotIndex >= 0) {
                    return false;
                }
            }

            if ((i == eIndex + 1) && (c == '+' || c == '-')) {
                continue;
            }

            if (c == '.') {

                if (dotIndex >= 0 || eIndex > 0) {
                    return false;
                } else if (i == len - 1 && !isDigit(s.charAt(i - 1))) {
                    return false;
                } else {
                    dotIndex = i;
                }

            } else if (c == 'e' || c == 'E') {

                if (eIndex > 0) {
                    return false;
                }

                if (i == 0 || i == len - 1) {
                    return false;
                }

                if (s.charAt(i - 1) != '.' && !isDigit(s.charAt(i - 1))) {
                    return false;
                }

                if (s.charAt(i - 1) == '.' && i == 1) {
                    return false;
                }

                eIndex = i;

            } else {
                return false;
            }
        }

        return true;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 1049. 最后一块石头的重量 II
     */
    public int lastStoneWeightII(int[] stones) {

        int sum = Arrays.stream(stones).sum();

        int n = stones.length;

        Set<Integer> set = new HashSet<>(2);
        set.add(stones[0]);
        set.add(-stones[0]);
        for (int i = 1; i < n; i++) {

            Set<Integer> tmpSet = new HashSet<>(set.size() << 1);
            for (Integer val : set) {
                tmpSet.add(val + stones[i]);
                tmpSet.add(val - stones[i]);
            }

            set = tmpSet;
        }

        return set.stream()
                .filter(x -> x >= 0)
                .min(Comparator.comparingInt(Integer::intValue)).orElse(0);
    }

    public int lastStoneWeightIIDp(int[] stones) {

        int n = stones.length;
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        
        int m = sum / 2;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= x) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - x] + x);
                }
            }
        }

        return Math.abs(sum - dp[n][m] - dp[n][m]);
    }

    /**
     * 1190. 反转每对括号间的子串
     */
    public String reverseParentheses(String s) {

        int n = s.length();

        StringBuilder builder = new StringBuilder();

        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(builder.toString());
                builder.setLength(0);
            } else if (c == ')') {
                builder.reverse();
                builder.insert(0, stack.pop());
            } else {
                builder.append(c);
            }

        }

        return builder.toString();
    }

    /**
     * 1574. 删除最短的子数组使剩余数组有序
     */
    public int findLengthOfShortestSubarray(int[] arr) {

        int n = arr.length;

        int prefix = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                prefix = i - 1;
                break;
            }
        }

        int suffix = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                suffix = i + 1;
                break;
            }
        }

        int maxElement = Math.max(prefix + 1, n - suffix);
        for (int i = prefix; i >= 0; i--) {
            for (int j = suffix; j < n; j++) {
                if (arr[j] >= arr[i]) {
                    maxElement = Math.max(maxElement, i + 1 + n - j);
                    break;
                }
            }
        }

        return n - maxElement;
    }

    /**
     * 1734. 解码异或后的排列
     */
    public int[] decode(int[] encoded) {

        int n = encoded.length + 1;

        int[] decoded = new int[n];

        int first = getXorSum(n);
        for (int i = 1; i < n; i += 2) {
            first = first ^ encoded[i];
        }

        decoded[0] = first;
        for (int i = 1; i < n; i++) {
            decoded[i] = decoded[i - 1] ^ encoded[i - 1];
        }

        return decoded;
    }

    private int getXorSum(int n) {

        switch (n % 4) {
            case 0:
                return n;
            case 1:
                return 1;
            case 2:
                return n + 1;
            case 3:
            default:
                return 0;
        }
    }

    /**
     * 1487.保证文件唯一
     */
    public String[] getFolderNames(String[] names) {

        int n = names.length;
        Map<String, Integer> nameCount = new HashMap<>(n);

        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            String name = names[i];
            Integer count = nameCount.getOrDefault(name, 0);
            // 没有重名文件
            if (count == 0) {
                nameCount.put(name, 1);
                ans[i] = name;
                continue;
            }

            // 为当前文件添加后缀直到没有重名文件
            String newName = name + "(" + count + ")";
            while (nameCount.containsKey(newName)) {
                newName = name + "(" + ++count + ")";
            }

            nameCount.put(name, ++count);
            nameCount.put(newName, 1);
            ans[i] = newName;
        }

        return ans;
    }

    public int shipWithinDays(int[] weights, int D) {

        int n = weights.length;

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(max, weight);
        }

        int low = max;
        int high = sum;

        while (low < high) {

            int mid = low + (high - low) / 2;
            // cur表示当天已载重的包裹重量
            int cur = 0;
            // days表示需要运完所有包括的天数
            int days = 1;
            for (int weight : weights) {
                if (mid < cur + weight) {
                    days++;
                    cur = 0;
                }
                cur += weight;
            }

            if (days <= D) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public int combinationSum4(int[] nums, int target) {

        int n = nums.length;

        // dp[i]表示总和为 i 的元素组合的个数
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {

            // 假设总和为 i 的一种组合的最后一个元素是num, 则对于总和为 i - num 的每一种组合
            // 在最后添加 num 之后即可得到一个元素之和等于 i 的排列
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }

        }

        return dp[target];
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {

        int n = nums.length;

        Arrays.sort(nums);

        // dp[i] 表示以nums[i]为最大整数的整除子集的数量
        int[] dp = new int[n];
        // 每个子集必须包含nums[i]
        Arrays.fill(dp, 1);

        int maxSize = 1;
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxIndex = i;
            }
        }

        LinkedList<Integer> subset = new LinkedList<>();

        for (int i = maxIndex; i >= 0; i--) {
            if (dp[i] == maxSize && nums[maxIndex] % nums[i] == 0) {
                maxIndex = i;
                maxSize--;
                subset.addFirst(nums[maxIndex]);
            }
        }

        return subset;
    }

    public int numDecodings(String s) {

        int n = s.length();

        // dp[i] 表示从0开始长度为i的子串的解码方法的总数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {

            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            if (i > 1) {
                if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')) {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[n];
    }

    public int rob(int[] nums) {

        int n = nums.length;

        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(rob(nums, 0, n - 1), rob(nums, 1, n));
    }

    private int rob(int[] nums, int start, int end) {

        int first = nums[start];
        int second = Math.max(first, nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int temp = Math.max(second, first + nums[i]);
            first = second;
            second = temp;
        }

        return second;
    }

    public int nthUglyNumber2(int n) {

        // dp[i]表示第i个丑数
        int[] dp = new int[n + 1];
        dp[1] = 1;

        // 指针px(x ∈ {2, 3, 5}) 表示满足dp[j] * x > dp[i - 1] 最小的下标j
        int p1 = 1, p2 = 1, p3 = 1;
        for (int i = 2; i <= n; i++) {

            int v1 = dp[p1] * 2, v2 = dp[p2] * 3, v3 = dp[p3] * 5;
            dp[i] = Math.min(v1, Math.min(v2, v3));

            if (dp[i] == v1) {
                p1++;
            }

            if (dp[i] == v2) {
                p2++;
            }

            if (dp[i] == v3){
                p3++;
            }

        }

        return dp[n];
    }

    public int nthUglyNumber(int n) {

        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;

        public TreeNode() {}

        TreeNode(int x) { val = x; }
   }

}
