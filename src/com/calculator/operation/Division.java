package com.calculator.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division implements Operation {
    @Override
    public BigDecimal execute(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.divide(secondOperand, 100, RoundingMode.HALF_UP);
    }
}
