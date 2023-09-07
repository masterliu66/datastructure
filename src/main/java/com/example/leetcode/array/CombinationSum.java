package com.example.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 */
public class CombinationSum {

    @Test
    public void test() {

        assert combinationSum(new int[]{2, 3, 6, 7}, 7).toString().equals("[[7], [2, 2, 3]]");

        assert combinationSum(new int[]{2, 3, 5}, 8).toString().equals("[[3, 5], [2, 3, 3], [2, 2, 2, 2]]");

        assert combinationSum(new int[]{2}, 1).toString().equals("[]");
    }

    int[] candidates;

    List<List<Integer>> ans;

    List<Integer> current;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        this.candidates = candidates;
        this.ans = new ArrayList<>();
        this.current = new ArrayList<>();

        dfs(0, target);

        return ans;
    }

    private void dfs(int index, int target) {

        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }

        // 不选当前数
        if (index < candidates.length - 1) {
            dfs(index + 1, target);
        }

        // 选择当前数
        if (candidates[index] <= target) {
            current.add(candidates[index]);
            dfs(index, target - candidates[index]);
            current.remove(current.size() - 1);
        }
    }

}
