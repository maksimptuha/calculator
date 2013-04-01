package com.calculator.view;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ViewResolver {
    private Scanner inputStream;
    private PrintStream outputStream;

    public ViewResolver(InputStream inputStream, PrintStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = outputStream;
    }

    public String printInput() {
        return inputStream.nextLine();
    }

    public void printOutput(String expression) {
        outputStream.println(expression);
    }
}
