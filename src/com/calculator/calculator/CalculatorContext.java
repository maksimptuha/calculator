package com.calculator.calculator;

import com.calculator.classLoader.OperationLoader;
import com.calculator.operation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CalculatorContext {
    private Map<String, Operation> addedOperations = new HashMap<String, Operation>();
    private OperationLoader operationLoader = new OperationLoader();

    private String addOperation(String operation) {
        Operation addOperation = operationLoader.loadOperation(operation);

        addedOperations.put(operation, addOperation);
        return "Operation " + operation  + " is added.";
    }

    private String executeOperation(String operation, String firstOperand, String secondOperand) {
        Operation executeOperation = addedOperations.get(operation);
        BigDecimal first = new BigDecimal(firstOperand);
        BigDecimal second = new BigDecimal(secondOperand);

        return executeOperation.execute(first, second).toString();
    }


    public String execute(String expression, String separator) {
        String[] operands = expression.split(separator);
        String result;

        if(operands[0].equals("add")) {
            result = addOperation(operands[1]);
        } else {
            result = executeOperation(operands[1], operands[0], operands[2]);
        }

        return result;
    }
}
