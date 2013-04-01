package com.calculator.operation;

import java.math.BigDecimal;

public class Multiplication implements Operation {
    @Override
    public BigDecimal execute(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.multiply(secondOperand);
    }
}
