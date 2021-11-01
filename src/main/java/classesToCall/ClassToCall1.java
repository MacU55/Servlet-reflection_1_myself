package classesToCall;

import annotations.MethodAnnotation;
import annotations.ParameterAnnotation;

public class ClassToCall1 {

    @MethodAnnotation("method")
    public String callTest(@ParameterAnnotation("label") String arg1, @ParameterAnnotation("name")String arg2){
        return "result calling method callTest, " + "arg1= "+ arg1 + "\t arg2= "+ arg2;
    }
}
