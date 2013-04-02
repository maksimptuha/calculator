package com.calculator.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division implements Operation {
    @Override
    public BigDecimal execute(BigDecimal firstOperand, BigDecimal secondOperand) {
        if(secondOperand.intValue() == 0) {
            throw new RuntimeException("Division by zero.");
        }

        return firstOperand.divide(secondOperand, 20, RoundingMode.HALF_UP);
    }
}
