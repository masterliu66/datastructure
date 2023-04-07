package com.example.test;


/**
 * className Calculator
 * packageName com.example.test
 * description
 *
 * @author CCC
 * @date 2020/8/11 21:56
 */
public class Calculator {

    private final char LEFT_BRACKETS = '(';

    private final char RIGHT_BRACKETS = ')';

    private final ArrayStack<Integer> numStack;

    private final ArrayStack<Character> operStack;

    public static void main(String[] args) {

        String expression = "(16 - 12) + 5 * (8 - 3) + 8 / 4 + -7";

        try {
            Calculator.doCalculate(expression);
        } catch (Exception e) {
            System.out.printf("%s %s\n", expression, "表达式存在非法的字符");
        }

        expression = "-6 - (4 / 2 * 2 * 3 - 2 * 8) + 4 * 8 + 2";

        try {
            Calculator.doCalculate(expression);
        } catch (Exception e) {
            System.out.printf("%s %s\n", expression, "表达式存在非法的字符");
        }
    }

    private Calculator(int size) {
        super();
        this.numStack = new ArrayStack<>(new Integer[size]);
        this.operStack = new ArrayStack<>(new Character[size]);
    }

    public static void doCalculate(String expression) throws RuntimeException {
        new Calculator(expression.length()).calculate(expression);
    }

    private void calculate(String expression) throws RuntimeException {

        // 去除表达式中的空格
        String expr = expression.replaceAll("\\s*", "");

        // 将中缀表达式数字和符号各自入栈
        pushExpressionCharToStack(expr);

        // 将剩余的表达式进行计算
        while (!operStack.empty()) {
            calculate();
        }

        // 输出结果
        System.out.printf("%s = %d\n", expression, numStack.pop());
    }

    private void pushExpressionCharToStack(String expr) throws RuntimeException {

        StringBuilder numBuilder = new StringBuilder();

        final char negative = '-';

        int index = 0;
        do {

            // 依次获取中缀表达式中的字符
            char c = expr.charAt(index);

            // 如果字符为负号(为中缀表达式的第一个字符或前一个字符为符号), 将其拼接到StringBuilder中
            if (c == negative) {
                if (index == 0 || isOper(expr.charAt(index - 1))) {
                    numBuilder.append(c);
                    index++;
                    continue;
                }
            }

            // 如果字符为符号, 入符号栈, 并继续下一轮循环
            if (isOper(c)) {
                pushToOperStack(c);
                index++;
                continue;
            }

            // 将数字拼接到StringBuilder中
            numBuilder.append(c);

            // 如果为中缀表达式最后一个字符, 直接入数栈, 并跳出整个循环
            if (index == expr.length() - 1) {
                pushToNumStatck(Integer.parseInt(numBuilder.toString()));
                break;
            }

            // 如果后一个字符为符号, 将该数字入数栈, 并将StringBuider置空
            if (isOper(expr.charAt(index + 1))) {
                pushToNumStatck(Integer.parseInt(numBuilder.toString()));
                numBuilder.delete(0, numBuilder.length());
            }

            index++;

        } while (index < expr.length());
    }

    private void pushToOperStack(char c) throws RuntimeException {

        // 如果符号栈为空, 或当前字符为左括号, 或符号栈栈顶为左括号, 将符号直接入栈
        if (operStack.empty() || c == LEFT_BRACKETS || operStack.peek() == LEFT_BRACKETS) {
            operStack.push(c);
            return;
        }

        // 如果符号为右括号, 直到栈顶为左括号为止, 将一对括号之间的表达式进行计算, 并将符号栈栈顶的左括号弹出
        if (c == RIGHT_BRACKETS) {
            while (operStack.peek() != LEFT_BRACKETS) {
                calculate();
            }
            operStack.pop();
            return;
        }

        // 获取符号的优先级
        int priority = getPriorityOfOper(c);

        /* 如果符号的优先级小于等于栈顶符号的优先级, 直到满足以下一个条件为止, 将两个符号之间的表达式进行计算
         * 1.符号栈为空
         * 2.栈顶为左括号
         * 3.栈顶符号的优先级大于符号的优先级
         */
        if (priority <= getPriorityOfOper(operStack.peek())) {
            do {
                calculate();
            } while (!operStack.empty() && operStack.peek() != LEFT_BRACKETS
                    && priority <= getPriorityOfOper(operStack.peek()));
        }

        operStack.push(c);
    }

    private void pushToNumStatck(int num) {
        numStack.push(num);
    }

    /**
     * 从数栈中弹出两个元素, 从符号栈中弹出一个元素, 进行四则运算后将计算结果入数栈
     */
    private void calculate() throws RuntimeException {

        int num1 = numStack.pop();
        int num2 = numStack.pop();
        char oper = operStack.pop();

        int result = 0;

        switch (oper) {
            case '+' :
                result = num1 + num2;
                break;
            case '-' :
                result = num2 - num1;
                break;
            case '*' :
                result = num1 * num2;
                break;
            case '/' :
                result = num2 / num1;
                break;
            default:
                break;
        }

        numStack.push(result);
    }

    /**
     * 获取一个符号的优先级
     * @param oper 符号
     * @return 符号的优先级
     */
    private int getPriorityOfOper(char oper) {

        int priority = 0;

        switch (oper) {
            case '*' :
            case '/' :
                priority = 1;
                break;
            case '+' :
            case '-' :
            default:
                break;
        }

        return priority;
    }

    /**
     * 判断一个字符是否为符号
     * @param c 字符
     * @return boolean
     */
    private boolean isOper(char c) {

        switch (c) {
            case '+' :
            case '-' :
            case '*' :
            case '/' :
            case LEFT_BRACKETS :
            case RIGHT_BRACKETS :
                return true;
            default:
                break;
        }

        return false;
    }

}
