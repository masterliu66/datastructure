package com.example.datastructure;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mysql.cj.util.TimeUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DemoTest {

    @Test
    public void test01() {
        String v = null;
        String num = "1";
        if ((v = num) == null) {
            System.out.println("null");
        } else if (v.equals("1")) {
            System.out.println("1");
        }
    }

    @Test
    public void test02() {
        String str = "1 2 3";
        StringTokenizer tokenizer = new StringTokenizer(str);
        boolean hasMoreTokens = tokenizer.hasMoreTokens();
        while (hasMoreTokens) {
            System.out.println(tokenizer.nextToken());
            hasMoreTokens = tokenizer.hasMoreTokens();
        }
    }

    @Test
    public void test03() {
        List<String> list = List.of("1", "2", "3", "4");

        List<String> newList = list.stream().skip(2).limit(2).collect(Collectors.toList());

        System.out.println(newList);

        newList.add("5");

        System.out.println(newList instanceof ArrayList);
    }

    @Test
    public void test04() {

        System.out.println(new SimpleDateFormat("yyMMdd").format(new Date()));

    }

    @Test
    public void test05() {

        List<String> empty = new ArrayList<>();

        empty.forEach(System.out::println);

    }

    @Test
    public void test06() {

        try {
            TimeUnit.SECONDS.sleep(1);
            int i = 1 / 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
