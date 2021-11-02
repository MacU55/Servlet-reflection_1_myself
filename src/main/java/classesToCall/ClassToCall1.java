package classesToCall;

import annotations.MethodAnnotation;
import annotations.ParameterAnnotation;

public class ClassToCall1 {

    @MethodAnnotation("method")
    public String callTest(@ParameterAnnotation("label") String arg1){
        return "result calling method callTest, " + "arg1= "+ arg1;
    }

    @MethodAnnotation("method")
    public String callTest2(@ParameterAnnotation("label") String arg1, @ParameterAnnotation("name")String arg2){
        return "result calling method callTest, " + "arg1= "+ arg1 + "\t arg2= "+ arg2;
    }

    @MethodAnnotation("method")
    public String callTest3(@ParameterAnnotation("label") String arg1, @ParameterAnnotation("name")String arg2, @ParameterAnnotation("nick")String arg3){
        return "result calling method callTest, " + "arg1= "+ arg1 + "\t arg2= "+ arg2 + "\t arg3= "+ arg3;
    }
}
