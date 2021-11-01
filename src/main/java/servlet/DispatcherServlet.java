package servlet;

import utils.AnnotatedCaller;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    AnnotatedCaller caller;


    public void init(ServletConfig config) {

        String[] classesArray = config.getInitParameter("handler").split("\n");
        List<Class<?>> classes = new ArrayList<>();
        for (String stringNameClass : classesArray) {
            Class aClass1 = stringNameClass.getClass();
            classes.add(aClass1);
        }
        caller = new AnnotatedCaller(classes);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        Object result;
        Map<String, String[]> parameterMap = request.getParameterMap();
        String pathInfo = request.getPathInfo();

        result = caller.methodCall(pathInfo, parameterMap);


    }


}
