package com.example.datastructure.algorithm.greedy;

import java.util.*;

/**
 * className GreedyAlgorithm
 * packageName com.example.datastructure.algorithm.greedy
 * description
 *
 * @author CCC
 * @date 2021/1/11 23:05
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {

        Map<String, List<String>> broadcasts = new HashMap<>(5);
        broadcasts.put("K1", Arrays.asList("北京", "上海", "天津"));
        broadcasts.put("K2", Arrays.asList("广州", "北京", "深圳"));
        broadcasts.put("K3", Arrays.asList("成都", "上海", "杭州"));
        broadcasts.put("K4", Arrays.asList("上海", "天津"));
        broadcasts.put("K5", Arrays.asList("杭州", "大连"));

        // 剩余未覆盖的所有区域
        Set<String> allAreas = new HashSet<>();
        broadcasts.values().forEach(allAreas::addAll);

        Set<String> tempAreas = new HashSet<>();
        String maxKey;
        int maxCount;

        List<String> selects = new ArrayList<>();
        do {

            maxKey = null;
            maxCount = 0;

            for (Map.Entry<String, List<String>> entry : broadcasts.entrySet()) {

                tempAreas.clear();

                String key = entry.getKey();
                List<String> areas = entry.getValue();

                tempAreas.addAll(areas);
                // 与剩余的未覆盖的所有地区取交集
                tempAreas.retainAll(allAreas);

                int size = tempAreas.size();

                // 选取一个剩余未覆盖区域最多的一个广播电台
                if (size > 0 && (maxKey == null || size > maxCount)) {
                    maxKey = key;
                    maxCount = size;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                // 将已覆盖的区域移除
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }  while (!allAreas.isEmpty());

        System.out.println(selects);
    }

}
