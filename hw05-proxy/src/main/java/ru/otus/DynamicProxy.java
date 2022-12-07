package ru.otus;

import ru.otus.annotations.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class DynamicProxy {

    private DynamicProxy() {
    }

    public static <T, I> I createInstance(T target, Class<I> iClass) {
        ClassLoader classLoader = iClass.getClassLoader();
        InvocationHandler handler = new LoggingInvocationHandler(target);

        return iClass.cast(Proxy.newProxyInstance(
                classLoader,
                new Class<?>[]{iClass},
                handler));
    }

    static class LoggingInvocationHandler implements InvocationHandler {
        private static final String PRINT_ARGS_TEMPLATE = "[ %s ]\n";
        private static final String ARGS_DELIMITER = ", ";
        private final Object wrappedObject;
        private final Map<Method, String> methodsSignature;
        private final Set<String> loggedMethodsSignature;

        LoggingInvocationHandler(Object wrappedObject) {
            this.wrappedObject = wrappedObject;
            methodsSignature = new HashMap<>();
            loggedMethodsSignature = getMarkedMethodsSignature(wrappedObject.getClass(), Log.class);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodSignature = getMethodSignature(method);
            if (loggedMethodsSignature.contains(methodSignature)) {
                printArgs(args);
            }
            return method.invoke(wrappedObject, args);
        }

        private String getMethodSignature(Method method) {
            String methodSignature = methodsSignature.get(method);

            if (methodSignature == null) {
                String methodName = method.getName();
                String returnType = method.getReturnType().getTypeName();
                String parametersType = Arrays.toString(method.getParameterTypes());
                methodSignature = String.format("%s-%s-%s", methodName, returnType, parametersType);
                methodsSignature.put(method, methodSignature);
            }

            return methodSignature;
        }

        private Set<String> getMarkedMethodsSignature(Class<?> clazz, Class<? extends Annotation> annotationClass) {
            Method[] methods = clazz.getDeclaredMethods();

            return Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(annotationClass))
                    .map(this::getMethodSignature)
                    .collect(Collectors.toSet());
        }

        private void printArgs(Object[] args) {
            String joinedArgs = "";

            if (args != null) {
                joinedArgs = Arrays.stream(args)
                        .map(Object::toString)
                        .collect(Collectors.joining(ARGS_DELIMITER));
            }

            System.out.printf(PRINT_ARGS_TEMPLATE, joinedArgs);
        }
    }
}
