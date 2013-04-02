package com.calculator.calculator;

import com.calculator.classLoader.OperationLoader;
import com.calculator.parser.Parser;
import com.calculator.view.ViewResolver;

public class Calculator {
    private static final String SEPARATOR = " ";

    private ViewResolver view = new ViewResolver(System.in, System.out);
    private Parser parser = new Parser();
    private CalculatorContext context;

    public boolean initialize() {
        try{
            OperationLoader loader = new OperationLoader();
            context = new CalculatorContext(loader);
            return true;
        } catch (RuntimeException exception) {
            view.printOutput(exception.getMessage());
            return false;
        }
    }

    public void start() {
        if(!initialize()) {
            return;
        }

        while (true) {
            String expression = view.printInput();

            try {
                parser.parse(expression, SEPARATOR);
                view.printOutput(context.execute(expression, SEPARATOR));
            } catch (RuntimeException exception) {
                view.printOutput(exception.getMessage());
                continue;
            }
        }
    }
}
