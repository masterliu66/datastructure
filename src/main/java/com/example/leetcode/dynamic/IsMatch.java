package com.example.leetcode.dynamic;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 10. 正则表达式匹配
 * 包含'.' 、 '*' 、 '+' 和 '?'
 */
public class IsMatch {

    final char[] words = {'a', 'b', 'c'};

    final char[] keyWords = {'*', '+', '?'};

    final Random random = new Random();

    final int len = 100;

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            var tuple = generate();
            String s = tuple._1;
            String p = tuple._2;
            Pattern pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(s);
            assert matcher.matches() == isMatch(s, p);
        }
    }

    private Tuple2<String, String> generate() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(words[random.nextInt(words.length)]);
        }
        String s = builder.toString();
        for (int i = 0; i < builder.length(); i += random.nextInt(len >> 1)) {
            builder.setCharAt(i, '.');
        }
        StringBuilder patternBuilder = new StringBuilder();
        int n = 0;
        boolean flag = false;
        while (n < builder.length()) {
            patternBuilder.append(flag ? keyWords[random.nextInt(keyWords.length)] : builder.charAt(n));
            flag = !flag;
            n += random.nextInt(len >> 2);
        }
        String p = patternBuilder.toString();
        return Tuple.of(s, p);
    }

    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();
        // f[i][j]表示s前i-1个字符和p前j-1个字符是否可以匹配
        boolean[][] f = new boolean[m+1][n+1];
        /* p[j] = . 则 f[i][j] = f[i-1][j-1]
         * p[j] = s[i] 则 f[i][j] = f[i-1][j-1]
         * p[j] = * 且 p[j-1]∉(s[i], .) 则 f[i][j] = f[i][j-2]
         * p[j] = * 且 p[j-1]∈(s[i], .) 则 f[i][j] = f[i-1][j] or f[i][j-2]
         * p[j] != s[i] 则 f[i][j] = false
         */
        // 初始值, s和p都为空串
        f[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c2 = p.charAt(j - 1);
                if (match(s, p, i, j)) {
                    f[i][j] = f[i-1][j-1];
                } else if (c2 == '*') {
                    // 不匹配
                    f[i][j] = f[i][j-2];
                    if (match(s, p, i, j - 1)) {
                        // 匹配一个及多个(f[i-1][j]已经包含了f[i-1][j-2])
                        f[i][j] = f[i][j] || f[i-1][j];
                    }
                } else if (c2 == '+') {
                    if (match(s, p, i, j - 1)) {
                        // 匹配一个或匹配多个
                        f[i][j] = f[i-1][j-2] || f[i-1][j];
                    }
                } else if (c2 == '?') {
                    // 不匹配
                    f[i][j] = f[i][j-2];
                    if (match(s, p, i, j - 1)) {
                        // 匹配一个
                        f[i][j] = f[i][j] || f[i-1][j-2];
                    }
                }
            }
        }

        return f[m][n];
    }

    private boolean match(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
