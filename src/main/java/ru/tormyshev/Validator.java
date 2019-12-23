package ru.tormyshev;

import java.util.*;

class Validator {

    boolean isValid () {

        String toValidate = normalizeString();
        if (toValidate.endsWith("+")||toValidate.endsWith("-")||
                toValidate.endsWith("/") || toValidate.endsWith("*")) {
            throw new IllegalArgumentException("Incorrect expression.");
        }
        if (areBracketsEmpty()) {
            throw new IllegalArgumentException("There is empty expression in brackets.");
        }
        if (!areBracketsBalanced()) {
            throw new IllegalArgumentException("Brackets are not balanced.");
        }
        String[] expressionToValidate = splitString();
        for (String operand : expressionToValidate) {
            try {
                Double.parseDouble(operand);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Incorrect expression.");
            }
        }

        return true;
    }
    private String[] splitString() {
        return normalizeString().split("[+\\-*/]");
    }
    private String normalizeString() {
        String stringToNormalize = Main.getEnteredExpression();
        stringToNormalize = stringToNormalize.replaceAll("\\(-|[()]", "");
        return stringToNormalize;
    }
    private boolean areBracketsEmpty() {
        String expressionToValidate = Main.getEnteredExpression();
        return expressionToValidate.contains("()");
    }
    private boolean areBracketsBalanced() {

        String expressionToValidate = Main.getEnteredExpression();
        Deque<String> stack = new ArrayDeque<>();
        String delimiters = "()";
        StringTokenizer tokenizer = new StringTokenizer(expressionToValidate, delimiters, true);
        String currentToken;
        while (tokenizer.hasMoreTokens()) {
            currentToken = tokenizer.nextToken();
            if (currentToken.equals("(")) stack.push(currentToken);
            else if (currentToken.equals(")")) {
                try {
                    stack.pop();
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
