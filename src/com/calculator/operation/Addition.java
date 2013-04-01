package com.calculator.operation;

import java.math.BigDecimal;

public class Addition implements Operation {
    @Override
    public BigDecimal execute(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.add(secondOperand);
    }
}
