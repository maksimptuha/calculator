package com.calculator.parser;

public class Parser {
    private Validator validator = new Validator();

    public void parse(String expression, String separator) {
        String[] operands = expression.split(separator);

        if(operands[0].equals("add")) {
            validator.addOperationValidate(operands[1]);
        } else {
            validator.validate(operands);
        }
    }
}
