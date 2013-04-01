package com.calculator.classLoader;

import com.calculator.operation.Operation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class OperationLoader extends ClassLoader {
    private final static String MODULE_PATH = "/modules/";

    public OperationLoader() {
        super(OperationLoader.class.getClassLoader());
    }

    public Class loadClass(String operation) throws ClassNotFoundException {
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

        return findClass(operationName);
    }

    @Override
    public Class findClass(String operationName) {
        byte classByte[];
        Class operationClass;

        try {
            JarFile jar = new JarFile(MODULE_PATH + operationName + ".jar");
            JarEntry entry = jar.getJarEntry(operationName + ".class");
            InputStream is = jar.getInputStream(entry);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = is.read();
            while (-1 != nextValue) {
                byteStream.write(nextValue);
                nextValue = is.read();
            }

            classByte = byteStream.toByteArray();
            operationClass = defineClass(null, classByte, 0, classByte.length, null);
            return operationClass;
        } catch (Exception e) {
            throw new RuntimeException("findClass exception.");
        }
    }

    public static void main(String[] args) {
        OperationLoader operationLoader = new OperationLoader();
        Operation operation;

        try {
            operation = (Operation) operationLoader.loadClass("+").newInstance();
            System.out.println(operation.execute(new BigDecimal(5), new BigDecimal(2)));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
