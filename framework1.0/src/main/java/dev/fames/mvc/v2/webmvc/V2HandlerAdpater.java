package dev.fames.mvc.v2.webmvc;

import dev.fames.mvc.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class V2HandlerAdpater {

    public boolean supports(Object handler) {
        return (handler instanceof V2HandlerMapping);
    }

    public V2ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        V2HandlerMapping handlerMapping = (V2HandlerMapping) handler;

        // 形参列表
        Map<String, Integer> paramMapping = new HashMap<>();

        Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();

        for (int i = 0; i < pa.length; i++) {
            for (Annotation annotation : pa[i]) {
                if (annotation instanceof RequestParam) {
                    String paramName = ((RequestParam) annotation).value();
                    if (!"".equals(paramName.trim())) {
                        paramMapping.put(paramName, i);
                    }
                }
            }
        }

        Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> type = paramTypes[i];
            if (type == HttpServletRequest.class || type == HttpServletRequest.class) {
                paramMapping.put(type.getName(), i);
            }
        }

        // 自定义命名参数所在位置
        Map<String, String[]> reqParamterMap = request.getParameterMap();

        Object[] paramValues = new Object[paramTypes.length];

        for (Map.Entry<String, String[]> param : reqParamterMap.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", "");

            if (!paramMapping.containsKey(param.getKey())) {
                continue;
            }

            int index = paramMapping.get(param.getKey());
            paramValues[index] = caseStringValue(value, paramTypes[index]);
        }

        if (paramMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = request;
        }
        if (paramMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = request;
        }

        // 4.从 handler 中取出 Controller、method，然后反射调用
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramTypes);

        if (null == result) {
            return null;
        }

        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == V2ModelAndView.class;
        if (isModelAndView) {
            return (V2ModelAndView) result;
        } else {
            return null;
        }
    }

    private Object caseStringValue(String value, Class<?> clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (clazz == int.class) {
            return Integer.valueOf(value);
        } else {
            return null;
        }
    }

}
