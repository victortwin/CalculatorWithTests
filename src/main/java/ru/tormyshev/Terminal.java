package ru.tormyshev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

class Terminal {

    String readExpression() {

        String enteredExpression = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            enteredExpression = reader.readLine();
            if (enteredExpression == null || enteredExpression.length() == 0)
                throw new IllegalStateException("Expression isn't specified.");
            enteredExpression = enteredExpression.replaceAll(" ", "")
                    .replace("(-", "(0-");
            if (enteredExpression.charAt(0) == '-' || enteredExpression.charAt(0) == '+') {
                enteredExpression = "0" + enteredExpression;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return enteredExpression;
    }

    void printResult(BigDecimal calculationResult) {
        System.out.println("The result of calculation is " + calculationResult);
    }
}
