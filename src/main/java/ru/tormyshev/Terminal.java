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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enteredExpression;
    }

    String getPreparedString(String stringToPrepare) {
        if (stringToPrepare == null || stringToPrepare.length() == 0)
            throw new IllegalArgumentException("Expression is empty.");
        stringToPrepare = stringToPrepare.replaceAll("\\s", "")
                .replace("(-", "(0-").replace("(+", "(0+");
        if (stringToPrepare.charAt(0) == '-' || stringToPrepare.charAt(0) == '+') {
            stringToPrepare = "0" + stringToPrepare;
        }
        return stringToPrepare;
    }

    void printResult(BigDecimal calculationResult) {
        System.out.println("The result of calculation is " + calculationResult);
    }
}
