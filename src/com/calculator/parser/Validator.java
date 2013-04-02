package com.calculator.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {
    private static final List<String> OPERATION_LIST = Arrays.asList("+", "-", "*", "/");

    private List<String> operations = new ArrayList<String>();

    public void addOperationValidate(String operation) {
        if(operations.contains(operation)) {
            throw new RuntimeException("Operation " + operation + " is already exist.");
        }

        if(OPERATION_LIST.contains(operation)) {
            operations.add(operation);
        } else {
            throw new RuntimeException("Can not add " + operation + " operation.");
        }
    }

    public void validate(String[] operands) {
        if(operands.length != 3) {
            throw new RuntimeException("Expression must consist of three operands.");
        }

        if(!operations.contains(operands[1])) {
            throw new RuntimeException("There is no " + operands[1] + " operation");
        }

        try {
            new BigDecimal(operands[0]);
            new BigDecimal(operands[2]);
        } catch (NumberFormatException exception) {
            throw new RuntimeException("Wrong operand.");
        }
    }
}
