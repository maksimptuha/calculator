package com.calculator.classLoader;

import com.calculator.operation.Operation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class OperationLoader {
    private final static String MODULES_PATH = "modules/";
    private final static String OPERATION_CLASS_NAME_PREFIX = "com.calculator.operation.";

    private String getOperationName(String operation) {
        String operationName = "";

        switch (operation.charAt(0)) {
            case '+':
                operationName = "Addition";
                break;
            case '-':
                operationName = "Subtraction";
                break;
            case '*':
                operationName = "Multiplication";
                break;
            case '/':
                operationName = "Division";
                break;
        }

        return operationName;
    }

    public Operation loadOperation(String operation) {
        String operationName = getOperationName(operation);
        URL[] modulePath = new URL[1];

        try {
            modulePath[0] =  new File(MODULES_PATH + operationName).toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(modulePath);
            Class operationClass = classLoader.loadClass(OPERATION_CLASS_NAME_PREFIX + operationName);

            return (Operation) operationClass.newInstance();
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException("Module " + operation + " is not found.");
        } catch (MalformedURLException exception) {
            throw new RuntimeException("Module " + operation + " can not be loaded.");
        } catch (InstantiationException exception) {
            throw new RuntimeException("Module " + operation + " can not be loaded.");
        } catch (IllegalAccessException exception) {
            throw new RuntimeException("Module " + operation + " can not be loaded.");
        }
    }
}
