package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址。可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和
 * "192.168@1.1" 是 无效 IP 地址。
 * 0 <= s.length <= 3000 仅由数字组成
 * @author CCC
 */
public class RestoreIpAddresses {

    public static void main(String[] args) {

        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();

        System.out.println(restoreIpAddresses.restoreIpAddresses("101023"));
    }

    private List<String> results = new ArrayList<>();

    private String[] ips = new String[4];

    public List<String> restoreIpAddresses(String s) {

        int length = s.length();
        if (length < 3 || length > 12) {
            return results;
        }

        restoreIpAddresses(s, 0, 0);
        /*for (int i = 0; i < length - 3 && i < 3; i++) {

            if (preNumIsZero(s, 0, i + 1)) {
                break;
            }

            ips[0] = s.substring(0, i + 1);
            if (Integer.parseInt(ips[0]) > 0xff) {
                break;
            }

            for (int j = i + 1; j < length - 2 && j - i < 4; j++) {

                if (preNumIsZero(s, i + 1, j - i)) {
                    break;
                }

                ips[1] = s.substring(i + 1, j + 1);
                if (Integer.parseInt(ips[1]) > 0xff) {
                    break;
                }

                for (int k = j + 1; k < length - 1 && k - j < 4; k++) {

                    if (preNumIsZero(s, j + 1, k - j)) {
                        break;
                    }

                    ips[2] = s.substring(j + 1, k + 1);
                    if (Integer.parseInt(ips[2]) > 0xff) {
                        break;
                    }

                    for (int l = k + 1; l < length && l - k < 4; l++) {

                        if (preNumIsZero(s, k + 1, l - k)) {
                            break;
                        }

                        ips[3] = s.substring(k + 1, l + 1);
                        if (Integer.parseInt(ips[3]) > 0xff) {
                            break;
                        }

                        if (l != length - 1) {
                            continue;
                        }

                        results.add(String.join(".", ips));
                    }
                }

            }
        }*/

        return results;
    }

    /**
     * @param s ip地址字符串
     * @param begin 当前ip地址段在字符串的起始位置
     * @param index ips的索引
     */
    private void restoreIpAddresses(String s, int begin, int index) {

        for (int digitsCount = 1; digitsCount < 4; digitsCount++) {

            if (preNumIsZero(s, begin, digitsCount)) {
                break;
            }

            int end = begin + digitsCount;

            if (end > s.length()) {
                break;
            }

            ips[index] = s.substring(begin, end);
            if (Integer.parseInt(ips[index]) > 0xff) {
                break;
            }

            if (index < 3) {
                restoreIpAddresses(s, end, index + 1);
            }

            if (end == s.length()) {
                results.add(String.join(".", ips));
            }
        }

    }

    private boolean preNumIsZero(String s, int begin, int length) {

        if (length < 2) {
            return false;
        }

        return s.charAt(begin) == '0';
    }

}
