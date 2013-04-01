package com.calculator.operation;

import java.math.BigDecimal;

public class Subtraction implements Operation {
    @Override
    public BigDecimal execute(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.subtract(secondOperand);
    }
}
