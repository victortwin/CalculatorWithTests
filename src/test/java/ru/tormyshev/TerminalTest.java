package ru.tormyshev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

 class TerminalTest {

    private final Terminal terminal = new Terminal();


    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testEmptyExpression")
    @DisplayName("Test of the method Terminal.getPreparedString(String stringToPrepare) with empty expressions")
    void testEmptyExpression(String string){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                terminal.getPreparedString(string));
        assertThat("Expression is empty.", is(equalTo(exception.getMessage())));
    }

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testNotEmptyExpression")
    @DisplayName("Test of the method Terminal.getPreparedString(String stringToPrepare) with not empty expressions")
    void testNotEmptyExpression(String string){
        assertDoesNotThrow(() -> terminal.getPreparedString(string));
    }


    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testExpressionWithSpaces")
    @DisplayName("Test of the method Terminal.getPreparedString(String stringToPrepare) with spaces")
    void testExpressionWithSpaces(String expected, String expression){
        assertThat(expected, is(equalTo(terminal.getPreparedString(expression))));
    }

    @ParameterizedTest
    @MethodSource("ru.tormyshev.Providers#testExpressionWithUnaryMinusOrPlus")
    @DisplayName("Test of the method Terminal.getPreparedString(String stringToPrepare) with unary minus or plus")
    void testExpressionWithUnaryMinusOrPlus(String expected, String expression){
        assertThat(expected, is(equalTo(terminal.getPreparedString(expression))));
    }

}
