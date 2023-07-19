package com.example.datastructure;

import java.util.Random;

public class SecretDemo {

    public static void main(String[] args) {

        int secretLen = 6;

        char[] chars = new char[10 + 26 * 2];
        for (int i = 0; i < 10; i++) {
            chars[i] = (char) ('0' + i);
        }

        for (int i = 0; i < 26; i++) {
            chars[i + 10] = (char) ('A' + i);
            chars[i + 36] = (char) ('a' + i);
        }

        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < secretLen; i++) {
            builder.append(chars[random.nextInt(chars.length)]);
        }

        System.out.println(builder);
    }

}
