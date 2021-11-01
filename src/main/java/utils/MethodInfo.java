package utils;

public class MethodInfo {

    private Object handlerInstance;
    private String methodName;

    public Object getHandlerInstance() {
        return handlerInstance;
    }

    public void setHandlerInstance(Object handlerInstance) {
        this.handlerInstance = handlerInstance;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public MethodInfo(Object handlerInstance, String methodName) {
        this.handlerInstance = handlerInstance;
        this.methodName = methodName;
    }
}
