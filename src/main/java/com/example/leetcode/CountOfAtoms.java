package com.example.leetcode;

import java.util.*;

/**
 * 726. 原子的数量
 *
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 *
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 *
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，
 * 但 H1O2 这个表达是不可行的。
 *
 * 两个化学式连在一起是新的化学式。例如H2O2He3Mg4 也是化学式。
 *
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 *
 * 给定一个化学式formula ，返回所有原子的数量。格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），
 * 然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 *
 * 1 <= formula.length <= 1000
 * formula 由小写英文字母、数字 '(' 和 ')' 组成。
 * formula 是有效的化学式。
 *
 * @author CCC
 * @date 2021/7/5 21:00
 */
public class CountOfAtoms {

    public static void main(String[] args) {

        String formula = "K4(ON(SO3)2)2";

        System.out.println(new CountOfAtoms(formula).countOfAtoms());
    }

    private String formula;

    private int i;

    private int n;

    public CountOfAtoms(String formula) {
        this.formula = formula;
    }

    public String countOfAtoms() {
        return countOfAtoms(formula);
    }

    public String countOfAtoms(String formula) {

        this.i = 0;
        this.n = formula.length();
        this.formula = formula;

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> operStack = new ArrayDeque<>();

        while (i < n) {

            char c = formula.charAt(i);
            if (Character.isUpperCase(c)) {
                StringBuilder operBuilder = new StringBuilder();
                operBuilder.append(c);
                i++;
                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    operBuilder.append(formula.charAt(i));
                    i++;
                }
                operStack.push(operBuilder.toString());
                numStack.push(getNextNum());
            } else if (c == ')') {
                i++;
                int factor = getNextNum();
                Deque<String> operStackTmp = new ArrayDeque<>();
                Deque<Integer> numStackTmp = new ArrayDeque<>();
                String oper = operStack.pop();
                while (!"(".equals(oper)) {
                    operStackTmp.push(oper);
                    numStackTmp.push(numStack.pop());
                    oper = operStack.pop();
                }

                while (!numStackTmp.isEmpty()) {
                    operStack.push(operStackTmp.pop());
                    numStack.push(numStackTmp.pop() * factor);
                }
            } else {
                operStack.push(String.valueOf(c));
                i++;
            }

        }

        TreeMap<String, Integer> map = new TreeMap<>();
        while (!operStack.isEmpty()) {
            String oper = operStack.pop();
            Integer num = map.getOrDefault(oper, 0);
            map.put(oper, num + numStack.pop());
        }

        StringBuilder builder = new StringBuilder();
        map.forEach((k, v) -> {
            builder.append(k);
            if (v > 1) {
                builder.append(v);
            }
        });

        return builder.toString();
    }

    private int getNextNum() {

        StringBuilder numBuilder = new StringBuilder();
        while (i < n && Character.isDigit(formula.charAt(i))) {
            numBuilder.append(formula.charAt(i));
            i++;
        }

        return numBuilder.length() == 0 ? 1 : Integer.parseInt(numBuilder.toString());
    }

}
