package com.example.datastructure.collection;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListMap;

public class SkipList {

    @Test
    public void test() {

        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        System.out.println(map);
    }


}
