package com.example.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 20. 表示数值的字符串
 */
public class IsNumber {

    @Test
    public void test() {

        String[] trueAnswers = {"+100", "5e2", "-123", "3.1416", "-1E-16", "0123"};
        String[] falseAnswers = {"12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"};

        IsNumber isNumber = new IsNumber();
        for (String answer : trueAnswers) {
            assert isNumber.isNumber(answer);
        }
        for (String answer : falseAnswers) {
            assert !isNumber.isNumber(answer);
        }
    }

    // 初始状态
    private final int STATE_INITIAL = 0;
    // +/-符号
    private final int STATE_SYMBOL = 1;
    // e/E后面的+/-符号
    private final int STATE_SYMBOL_AFTER_E = 2;
    // 数字
    private final int STATE_DIGIT = 3;
    // 小数点后的数字
    private final int STATE_DIGIT_AFTER_POINT = 4;
    // e/E后面的数字
    private final int STATE_DIGIT_AFTER_E = 5;
    // 小数点.
    private final int STATE_POINT = 6;
    // 数字后面的小数点.
    private final int STATE_POINT_AFTER_DIGIT = 7;
    // e/E符号
    private final int STATE_E = 8;

    private final Map<Integer, Map<Character, Integer>> stateRules = new HashMap<>();

    public boolean isNumber(String s) {
        // 剔除前后空格
        s = s.trim();
        // 定义状态机转移规则
        addRules(STATE_INITIAL, '+', STATE_SYMBOL);
        addRules(STATE_INITIAL, '-', STATE_SYMBOL);
        addRules(STATE_INITIAL, '.', STATE_POINT);
        for (int i = 0; i < 10; i++) {
            addRules(STATE_INITIAL, (char) ('0' + i), STATE_DIGIT);
            addRules(STATE_SYMBOL, (char) ('0' + i), STATE_DIGIT);
            addRules(STATE_DIGIT, (char) ('0' + i), STATE_DIGIT);
            addRules(STATE_POINT, (char) ('0' + i), STATE_DIGIT_AFTER_POINT);
            addRules(STATE_POINT_AFTER_DIGIT, (char) ('0' + i), STATE_DIGIT_AFTER_POINT);
            addRules(STATE_DIGIT_AFTER_POINT, (char) ('0' + i), STATE_DIGIT_AFTER_POINT);
            addRules(STATE_SYMBOL_AFTER_E, (char) ('0' + i), STATE_DIGIT_AFTER_E);
            addRules(STATE_DIGIT_AFTER_E, (char) ('0' + i), STATE_DIGIT_AFTER_E);
            addRules(STATE_E, (char) ('0' + i), STATE_DIGIT_AFTER_E);
        }
        addRules(STATE_SYMBOL, '.', STATE_POINT);
        addRules(STATE_DIGIT, '.', STATE_POINT_AFTER_DIGIT);
        addRules(STATE_POINT_AFTER_DIGIT, 'e', STATE_E);
        addRules(STATE_POINT_AFTER_DIGIT, 'E', STATE_E);
        addRules(STATE_DIGIT, 'e', STATE_E);
        addRules(STATE_DIGIT, 'E', STATE_E);
        addRules(STATE_DIGIT_AFTER_POINT, 'e', STATE_E);
        addRules(STATE_DIGIT_AFTER_POINT, 'E', STATE_E);
        addRules(STATE_E, '+', STATE_SYMBOL_AFTER_E);
        addRules(STATE_E, '-', STATE_SYMBOL_AFTER_E);

        int state = STATE_INITIAL;
        for (int i = 0, l = s.length(); i < l; i++) {
            char c = s.charAt(i);
            state = getState(state, c);
            if (state == -1) {
                return false;
            }
        }

        // 定义最终合法状态
        int[] allowStates = {STATE_DIGIT, STATE_DIGIT_AFTER_POINT, STATE_DIGIT_AFTER_E, STATE_POINT_AFTER_DIGIT};

        return Arrays.binarySearch(allowStates, state) >= 0;
    }

   private void addRules(Integer from, Character c, Integer to) {
       stateRules.computeIfAbsent(from, key -> new HashMap<>()).put(c, to);
   }


    private int getState(int from, char c) {
        return stateRules.get(from).getOrDefault(c, -1);
    }
}
