package ru.tormyshev;

import java.util.*;

class Validator {

    boolean isValid (String enteredExpression) {

        String toValidate = normalizeString(enteredExpression);
        if (toValidate.endsWith("+")||toValidate.endsWith("-")||
                toValidate.endsWith("/") || toValidate.endsWith("*")) {
            throw new IllegalArgumentException("Incorrect expression.");
        }
        if (areBracketsEmpty(enteredExpression)) {
            throw new IllegalArgumentException("There is empty expression in brackets.");
        }
        if (!areBracketsBalanced(enteredExpression)) {
            throw new IllegalArgumentException("Brackets are not balanced.");
        }
        String[] expressionToValidate = splitString(enteredExpression);
        for (String operand : expressionToValidate) {
            try {
                Double.parseDouble(operand);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Incorrect expression.");
            }
        }

        return true;
    }
    private String[] splitString(String enteredExpression) {
        return normalizeString(enteredExpression).split("[+\\-*/]");
    }
    private String normalizeString(String stringToNormalize) {
        stringToNormalize = stringToNormalize.replaceAll("\\(-|[()]", "");
        return stringToNormalize;
    }
    private boolean areBracketsEmpty(String expressionToValidate) {
        return expressionToValidate.contains("()");
    }
    private boolean areBracketsBalanced(String expressionToValidate) {
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
