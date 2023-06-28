package com.xllwhitereaper.rest_api_test.utils;

public class Calculator {
    public int multiply(int... integersList) {
        int multiplicationValue = 1;
        for (int integer : integersList) {
            multiplicationValue *= integer;
        }
        return multiplicationValue;
    }

    public double divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException();
        }
        return (double) dividend / divisor;
    }
}
