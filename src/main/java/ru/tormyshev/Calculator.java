package ru.tormyshev;


import java.math.BigDecimal;
import java.util.Stack;
import java.util.StringTokenizer;

class Calculator {
    static BigDecimal calculateExpression(String expression) {
        String reversePolishNotation = Parser.sortingStation(expression);
        StringTokenizer tokenizer = new StringTokenizer(reversePolishNotation, " ");
        Stack<BigDecimal> stack = new Stack<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (!Parser.MATH_OPERATORS.containsKey(token)) {
                stack.push(new BigDecimal(token));
            } else {
                BigDecimal operand2 = stack.pop();
                BigDecimal operand1 = stack.pop();
                switch (token) {
                    case "*":
                        stack.push(operand1.multiply(operand2));
                        break;
                    case "/":
                        try {
                            stack.push(operand1.divide(operand2, 6, BigDecimal.ROUND_HALF_UP));
                        } catch (ArithmeticException e) {
                            throw new IllegalArgumentException("Expression contains division by zero.");
                        }
                        break;
                    case "+":
                        stack.push(operand1.add(operand2));
                        break;
                    case "-":
                        stack.push(operand1.subtract(operand2));
                        break;
                }
            }
        }
        if (stack.size() != 1)
            throw new IllegalArgumentException("Incorrect expression.");
        return stack.pop();
    }
}
