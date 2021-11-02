package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.AnnotatedMethodCaller;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    public AnnotatedMethodCaller caller;

    public void init(ServletConfig config) {
        String[] classesArray = config.getInitParameter("handler").split("\n");
        List<Class<?>> classes = new ArrayList<>();
        for (String stringNameClass : classesArray) {
           Class<?> aClass;
           try {
               aClass = Class.forName(stringNameClass);
           }catch (ClassNotFoundException e){
               throw new RuntimeException("could not instantiate class", e );
           }
            classes.add(aClass);
        }
        caller = new AnnotatedMethodCaller(classes);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pathInfo = request.getPathInfo().substring(1);
        Object result;

        Map<String, String> parameterMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            parameterMap.put(entry.getKey(), entry.getValue()[0]);

        }
        try {
            result = caller.methodCall(pathInfo, parameterMap);
        } catch (Exception e) {
            throw new RuntimeException("could not call method", e);
        }

        if (result instanceof String) {
            response.getWriter().print(result);
        } else {
            String serializedResonse = objectMapper.writeValueAsString(request);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(serializedResonse);
            out.flush();
        }
    }
}
