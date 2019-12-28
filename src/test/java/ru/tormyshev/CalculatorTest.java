package ru.tormyshev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CalculatorTest {

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testArithmeticalOperationsExceptDivision")
    @DisplayName("Test of the method Calculator.calculateExpression(String expression) except division")

    void testArithmeticalOperationsExceptDivision(BigDecimal expected, String expression) {
        assertThat(expected, is(equalTo(Calculator.calculateExpression(expression))));
    }

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testArithmeticalOperationsWithDivision")
    @DisplayName("Test of the method Calculator.calculateExpression(String expression) with division")
    void testArithmeticalOperationsWithDivision(BigDecimal expected, String expression) {
        assertThat(expected, is(equalTo(Calculator.calculateExpression(expression))));
    }


    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testDivideByZero")
    @DisplayName("Test of the method Calculator.calculateExpression(String expression) with division by zero")
    void testDivideByZero (String expression) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Calculator.calculateExpression(expression));
        assertThat("Expression contains division by zero.", is(equalTo(exception.getMessage())));
    }
}
