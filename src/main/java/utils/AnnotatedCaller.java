package utils;

import annotations.MethodAnnotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotatedCaller {

    Map<String, MethodInfo> mapHandler = new HashMap<>();

    public AnnotatedCaller(List<Class<?>> classesList) {
        MethodInfo methodInfo;
        Object newInstance;
        for (Class<?> target : classesList) {
            try {
                newInstance = target.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("", e);
            }
            for (Method method : target.getMethods()) {
                MethodAnnotation declaredAnnotation = method.getDeclaredAnnotation(MethodAnnotation.class);
                if (declaredAnnotation != null) {
                    String keyValue = declaredAnnotation.value();
                    methodInfo = new MethodInfo(newInstance, method.getName());
                    mapHandler.put(keyValue, methodInfo);
                }
            }
        }
    }

    public Object methodCall(String alias, Map<String, String[]> map) {

        MethodInfo methodInfo = mapHandler.get(alias);
        Object handlerInstance = methodInfo.getHandlerInstance();
        Method methodInit = null;
        Object result;
        String [] arguments;
        int i = 0;
        for(Method method : methodInfo.getClass().getMethods()){

        }

        return result = methodInit.invoke(handlerInstance, arguments);
    }


}
