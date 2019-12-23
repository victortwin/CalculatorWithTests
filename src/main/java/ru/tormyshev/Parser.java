package ru.tormyshev;

import java.util.*;

class Parser {
    static final Map<String, Integer> MATH_OPERATORS;

    static {
        MATH_OPERATORS = new HashMap<>();
        MATH_OPERATORS.put("*", 1);
        MATH_OPERATORS.put("/", 1);
        MATH_OPERATORS.put("+", 2);
        MATH_OPERATORS.put("-", 2);
    }

    static String sortingStation(String expression) {
        List<String> outputQueue = new ArrayList<>();

        Stack<String> stack = new Stack<>();

        Set<String> operationSymbols = new HashSet<>(Parser.MATH_OPERATORS.keySet());
        operationSymbols.add("(");
        operationSymbols.add(")");

        int lastIterationIndex = 0;

        boolean needToFindNext = true;
        while (needToFindNext) {
            int nextOperationIndex = expression.length();
            String nextOperation = "";
            for (String operation : operationSymbols) {
                int i = expression.indexOf(operation, lastIterationIndex);
                if (i >= 0 && i < nextOperationIndex) {
                    nextOperation = operation;
                    nextOperationIndex = i;
                }
            }
            if (nextOperationIndex == expression.length()) {
                needToFindNext = false;
            } else {
                if (lastIterationIndex != nextOperationIndex) {
                    outputQueue.add(expression.substring(lastIterationIndex, nextOperationIndex));
                }
                if (nextOperation.equals("(")) {
                    stack.push(nextOperation);
                }
                else if (nextOperation.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        outputQueue.add(stack.pop());
                        if (stack.empty()) {
                            throw new IllegalArgumentException("Brackets are not balanced.");
                        }
                    }
                    stack.pop();
                }
                else {
                    while (!stack.empty() && !stack.peek().equals("(") &&
                            (Parser.MATH_OPERATORS.get(nextOperation) >= Parser.MATH_OPERATORS.get(stack.peek()))) {
                        outputQueue.add(stack.pop());
                    }
                    stack.push(nextOperation);
                }
                lastIterationIndex = nextOperationIndex + nextOperation.length();
            }
        }
        if (lastIterationIndex != expression.length()) {
            outputQueue.add(expression.substring(lastIterationIndex));
        }
        while (!stack.empty()) {
            outputQueue.add(stack.pop());
        }
        StringBuilder reversePolishNotation = new StringBuilder();
        if (!outputQueue.isEmpty())
            reversePolishNotation.append(outputQueue.remove(0));
        while (!outputQueue.isEmpty())
            reversePolishNotation.append(" ").append(outputQueue.remove(0));

        return reversePolishNotation.toString();
    }
}
