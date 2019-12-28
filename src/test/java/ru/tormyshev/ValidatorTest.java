package ru.tormyshev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    private final Validator validator = new Validator();

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testNotEmptyBrackets")
    @DisplayName("Test of the method Validator.isValid(String enteredExpression) with not empty brackets")

    void testNotEmptyBrackets(String expression) {

        assertThat(true, is(equalTo(validator.isValid(expression))));
    }

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testEmptyBrackets")
    @DisplayName("Test of the method Validator.isValid(String enteredExpression) with empty brackets")
    void testEmptyBrackets (String expression) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validator.isValid(expression));
        assertThat("There is empty expression in brackets.", is(equalTo(exception.getMessage())));
    }

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testBalancedBrackets")
    @DisplayName("Test of the method Validator.isValid(String enteredExpression) with balanced brackets")

    void testBalancedBrackets(String expression) {

        assertThat(true, is(equalTo(validator.isValid(expression))));
    }

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testNotBalancedBrackets")
    @DisplayName("Test of the method Validator.isValid(String enteredExpression) with not balanced brackets")
    void testNotBalancedBrackets (String expression) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validator.isValid(expression));
        assertThat("Brackets are not balanced.", is(equalTo(exception.getMessage())));
    }

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testIncorrectExpression")
    @DisplayName("Test of the method Validator.isValid(String enteredExpression) with incorrect expression")
    void testIncorrectExpression (String expression) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validator.isValid(expression));
        assertThat("Incorrect expression.", is(equalTo(exception.getMessage())));
    }
}
