package ru.otus;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface testLoggingInterface = DynamicProxy.createInstance(new TestLogging(), TestLoggingInterface.class);
        testLoggingInterface.calculation();
        testLoggingInterface.calculation(6);
        testLoggingInterface.calculation(5, 5);
        testLoggingInterface.calculation(10, 9, 8);
    }
}
