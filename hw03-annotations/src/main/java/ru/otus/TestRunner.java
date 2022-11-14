package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.utils.ReflectionHelper;
import ru.otus.utils.TestHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void runTests(String className) {
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            classificateTestMethods(methods, beforeMethods, afterMethods, testMethods);

            TestHelper testsResult = new TestHelper(testMethods.size());

            for (Method testMethod : testMethods) {
                Object testObject = ReflectionHelper.createInstance(clazz);
                String methodName = testMethod.getName();

                try {
                    ReflectionHelper.runMethods(testObject, beforeMethods);
                    ReflectionHelper.runMethod(testObject, testMethod);

                    testsResult.printSuccessTest(methodName);
                } catch (Exception ex) {
                    testsResult.printFailedTest(methodName);
                } finally {
                    try {
                        ReflectionHelper.runMethods(testObject, afterMethods);
                    } catch (Exception e) {
                        System.out.println("Возникла ошибка при выполнении @After методов.");
                    } finally {
                        testsResult.printDelimiter();
                    }
                }
            }
            testsResult.printResults();

        } catch (ClassNotFoundException ex) {
            System.out.printf("Класс %s не найден.\n", className);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                 IllegalArgumentException | InvocationTargetException ex) {
            System.out.printf("У класса %s нет публичного конструктора без аргументов.\n", className);
        }
    }

    private static void classificateTestMethods(Method[] methods, List<Method> beforeMethods,
                                                List<Method> afterMethods, List<Method> testMethods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }
    }
}
