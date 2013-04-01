package com.calculator.operation;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal execute(BigDecimal firstOperand, BigDecimal secondOperand);
}
