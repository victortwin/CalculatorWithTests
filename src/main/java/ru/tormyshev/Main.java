package ru.tormyshev;



public class Main {

    private static String enteredExpression;

    public static void main(String[] args) {
        System.out.println("Enter expression");
        Terminal terminal = new Terminal();
        enteredExpression = terminal.getPreparedString(terminal.readExpression());
        Validator validator = new Validator();

        if(validator.isValid(enteredExpression)) {
            try {
                terminal.printResult(Calculator.calculateExpression(enteredExpression));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
