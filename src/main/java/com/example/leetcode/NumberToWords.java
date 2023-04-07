package com.example.leetcode;

import org.junit.Test;

/**
 * 273. 整数转换英文表示
 *
 * @author CCC
 * @date 2021/10/11 22:29
 */
public class NumberToWords {

    @Test
    public void numberToWords() {
        int num = 12345;
        System.out.println(numberToWords(num));
    }

    private final int billion = 1_000_000_000;
    private final int million = 1_000_000;
    private final int thousand = 1_000;
    private final int hundred = 100;
    private final String[] words = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] unit = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {

        if (num == 0) {
            return words[0];
        }

        StringBuilder builder = new StringBuilder();
        // Billion
        if (num >= billion) {
            int digit = num / billion;
            num = num % billion;
            builder.append(words[digit]).append(" ").append("Billion");
        }

        if (num >= million) {
            int digit = num / million;
            num = num % million;
            appendSpace(builder);
            builder.append(numberToWordsLessThousand(digit)).append(" ").append("Million");
        }

        if (num >= thousand) {
            int digit = num / thousand;
            num = num % thousand;
            appendSpace(builder);
            builder.append(numberToWordsLessThousand(digit)).append(" ").append("Thousand");
        }

        if (num > 0) {
            appendSpace(builder);
            builder.append(numberToWordsLessThousand(num));
        }

        return builder.toString();
    }

    private String numberToWordsLessThousand(int num) {

        StringBuilder builder = new StringBuilder();
        if (num >= hundred) {
            int digit = num / hundred;
            num = num % hundred;
            builder.append(words[digit]).append(" ").append("Hundred");
        }

        if (num >= 20) {
            int digit = num / 10;
            num = num % 10;
            appendSpace(builder);
            builder.append(unit[digit]);
            if (num != 0) {
                builder.append(" ").append(words[num]);
            }
        } else if (num > 0) {
            appendSpace(builder);
            builder.append(words[num]);
        }

        return builder.toString();
    }

    private void appendSpace(StringBuilder builder) {
        if (builder.length() > 0) {
            builder.append(" ");
        }
    }

}
