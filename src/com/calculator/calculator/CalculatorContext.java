package com.calculator.calculator;

import com.calculator.operation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CalculatorContext {
    private Map<String, Operation> operations = new HashMap<String, Operation>();

    public String execute(String expression, String separator) {
        String[] operands = expression.split(separator);
        String result;

        if(operands[0].equals("add")) {
            result = addOperation(operands[1]);
        } else {
            Operation executeOperation = operations.get(operands[1]);
            BigDecimal firstOperand = new BigDecimal(operands[0]);
            BigDecimal secondOperand = new BigDecimal(operands[2]);

            result = executeOperation.execute(firstOperand, secondOperand).toString();
        }
        return result;
    }

    public String addOperation(String operation) {
        Operation addOperation = null;

        switch (operation.charAt(0)) {
            case '+':
                addOperation = new Addition();
                break;
            case '-':
                addOperation = new Subtraction();
                break;
            case '*':
                addOperation = new Multiplication();
                break;
            case '/':
                addOperation = new Division();
                break;
        }

        operations.put(operation, addOperation);
        return "Operation " + operation  + " is added.";
    }
}
