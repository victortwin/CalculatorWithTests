package ru.tormyshev;

import org.junit.jupiter.params.provider.Arguments;

import java.math.BigDecimal;
import java.util.stream.Stream;



import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Providers {
    private Providers() {

    }

    static Stream<Arguments> testArithmeticalOperationsExceptDivision() {
        return Stream.of(
                arguments(new BigDecimal("5"), "2 + 3"),
                arguments(new BigDecimal("9"), "3*3"),
                arguments(new BigDecimal("-1"), " 2  -   3 "),
                arguments(new BigDecimal("-148"), "0 - 50 * 3 + 2"),
                arguments(new BigDecimal("-148.009"), "0 - 50.003 * 3 + 2")
        );
    }

    static Stream<Arguments> testArithmeticalOperationsWithDivision() {
        return Stream.of(
                arguments(new BigDecimal("0.500000"), "2 + 3/2 - 1 * 3"),
                arguments(new BigDecimal("9.500000"), "2 + 5/(3 - 1) * 3")
        );
    }

    static Stream<Arguments> testEmptyExpression() {
        return Stream.of(
                arguments(""),
                arguments((String) null)
        );
    }

    static Stream<Arguments> testNotEmptyExpression() {
        return Stream.of(
                arguments("2+2"),
                arguments("-5.666 - 3 * 2")
        );
    }

    static Stream<Arguments> testExpressionWithSpaces() {
        return Stream.of(
                arguments("1+5/6*2.1", "1 + 5 / 6 * 2.1"),
                arguments("(1+5)/6*2.1", "  (  1  +  5  )/    6 *   2.1   ")
        );
    }

    static Stream<Arguments> testExpressionWithUnaryMinusOrPlus() {
        return Stream.of(
                arguments("0-1+5/6*2.1", "-1 + 5 / 6 * 2.1"),
                arguments("(0-1+5)/6*2.1", "(-1 + 5) / 6 * 2.1"),
                arguments("(0+1+5)/6*2.1", "(+1 + 5) / 6 * 2.1"),
                arguments("0+1+5/6*2.1", "+1 + 5 / 6 * 2.1")
        );
    }

    static Stream<Arguments> testDivideByZero() {
        return Stream.of(
                arguments("2 / 0"),
                arguments("5/(5-5)")
        );
    }

    static Stream<Arguments> testNotEmptyBrackets() {
        return Stream.of(
                arguments("3 + 5 / (5.1 + 6)"),
                arguments("(-3 + 5)")
        );
    }

    static Stream<Arguments> testEmptyBrackets() {
        return Stream.of(
                arguments("3 / 5 / (5.1 + 6) * ()"),
                arguments("() + 2.3")
        );
    }

    static Stream<Arguments> testBalancedBrackets() {
        return Stream.of(
                arguments("3 / 5 / (5.1 + 6) * (5)"),
                arguments("(1/2.33) + 2.3")
        );
    }
    static Stream<Arguments> testNotBalancedBrackets() {
        return Stream.of(
                arguments("3 / 5 / (5.1 + 6)) * (5)"),
                arguments("((1/2.33) + 2.3"),
                arguments(")(1/2.33) + 2.3"),
                arguments(")3+2(")
        );
    }

    static Stream<Arguments> testIncorrectExpression() {
        return Stream.of(
                arguments("2+3+"),
                arguments("5 + + 6"),
                arguments("qwerty"),
                arguments("8 / (5 + 3) -"),
                arguments("8 / - (5 + 3)")
        );
    }

}
