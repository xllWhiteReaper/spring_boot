package com.xllwhitereaper.rest_api_test.non_api_related;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xllwhitereaper.rest_api_test.utils.non_api_related.Calculator;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void shouldMultiply() {
        int actualAnswer = 12;
        int methodResponse = calculator.multiply(1, 2, 3, 2);
        assertEquals(methodResponse, actualAnswer);
    }

    @Test
    public void shouldDivide() {
        int dividend = 12;
        int divisor = 4;
        double division = 3;
        double methodResponse = calculator.divide(dividend, divisor);

        assertEquals(division, methodResponse);
    }

    @Test
    public void shouldThrowAnArithmeticExceptionWhen0IsDivisor() {
        int dividend = 12;
        int divisor = 0;
        assertThrows(ArithmeticException.class, () -> calculator.divide(dividend, divisor));
    }
}
