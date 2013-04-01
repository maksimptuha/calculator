package com.calculator.calculator;

import com.calculator.parser.Parser;
import com.calculator.view.ViewResolver;

public class Calculator {
    private final static String SEPARATOR = " ";

    private ViewResolver view = new ViewResolver(System.in, System.out);
    private Parser parser = new Parser();
    private CalculatorContext context = new CalculatorContext();

    public void start() {
        while (true) {
            String expression = view.printInput();

            try {
                parser.parse(expression, SEPARATOR);
            } catch (RuntimeException exception) {
                view.printOutput(exception.getMessage());
                continue;
            }

            view.printOutput(context.execute(expression, SEPARATOR));
        }
    }
}
