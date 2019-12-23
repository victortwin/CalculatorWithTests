package ru.tormyshev;

import exception.DivisionByZeroException;


public class Main {

    private static String enteredExpression;

    static String getEnteredExpression() {
        return enteredExpression;
    }

    public static void main(String[] args) {
        System.out.println("Enter expression");
        Terminal terminal = new Terminal();
        enteredExpression = terminal.readExpression();
        Validator validator = new Validator();

        if(validator.isValid()) {
            try {
                terminal.printResult(Calculator.calculateExpression(enteredExpression));
            } catch (DivisionByZeroException e) {
                e.printStackTrace();
            }
        }
    }
}
