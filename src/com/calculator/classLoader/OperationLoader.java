package com.calculator.classLoader;

import com.calculator.operation.Operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

public class OperationLoader {
    private final static String PROPERTIES_PATH = "properties/loader.properties";
    private String modulesPath;
    private String operationClassNamePrefix;

    public OperationLoader() throws IOException {
        Properties loaderProperties = new Properties();
        loaderProperties.load(new FileInputStream(PROPERTIES_PATH));
        modulesPath = loaderProperties.getProperty("modules_path");
        operationClassNamePrefix = loaderProperties.getProperty("operation_class_name_prefix");
    }

    public String getOperationName(String operation) {
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
            modulePath[0] =  new File(modulesPath + operationName).toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(modulePath);
            Class operationClass = classLoader.loadClass(operationClassNamePrefix + operationName);

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
