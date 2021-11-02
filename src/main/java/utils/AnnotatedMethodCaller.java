package utils;

import annotations.MethodAnnotation;
import annotations.ParameterAnnotation;
import exceptions.MethodNotFoundException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotatedMethodCaller {

    Map<String, MethodInfo> mapHandler = new HashMap<>();

    public AnnotatedMethodCaller(List<Class<?>> classesList) {

        Object newInstance;
        for (Class<?> target : classesList) {
            try {
                newInstance = target.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("could not create instance", e);
            }

            for (Method method : target.getMethods()) {
                MethodAnnotation declaredAnnotation = method.getDeclaredAnnotation(MethodAnnotation.class);
                if (declaredAnnotation != null) {
                    String keyValue = declaredAnnotation.value();
                    MethodInfo methodInfo = new MethodInfo(newInstance, method.getName());
                    mapHandler.put(keyValue, methodInfo);
                }
            }
        }
    }

    public Object methodCall(String alias, Map<String, String> parametersMap) {

        MethodInfo methodInfo;
        try {
            methodInfo = mapHandler.get(alias);
        } catch (MethodNotFoundException e) {
            throw new RuntimeException("method not found", e);
        }

        Method methodInit = null;
        Object result;

        String desiredMethodName = methodInfo.getMethodName();
        for (Method method : methodInfo.getHandlerInstance().getClass().getMethods()) {
            if (method.getName().equals(desiredMethodName)) {
                methodInit = method;
            }
        }

        int i = 0;
        int numberParameters = methodInit.getParameterCount();
        String[] arguments = new String[numberParameters];
        for (Parameter parameter : methodInit.getParameters()) {
            ParameterAnnotation declaredAnnotation = parameter.getDeclaredAnnotation(ParameterAnnotation.class);
            String key = declaredAnnotation.value();
            String value = parametersMap.get(key);
            arguments[i] = value;
            i++;
        }

        try {
            return result = methodInit.invoke(methodInfo.getHandlerInstance(), arguments);

        } catch (Exception e) {
            throw new RuntimeException("could not invoke method", e);
        }
    }
}
