package dev.frames.spring.web.servlet;

import dev.frames.spring.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求处理包装器
 *
 * @version 2019/5/9 16:46
 */

public class V2HandlerAdapter {


    public V2ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, V2HandlerMapping handlerMapping) throws InvocationTargetException, IllegalAccessException {
        // 获取方法参数位置, 每个方法需要注解支持
        Map<String, Integer> paramterIndex = new HashMap<>();
        Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation annotation : pa[i]) {
                if (annotation instanceof RequestParam) {
                    String paramterName = ((RequestParam) annotation).value().trim();
                    if (!"".equals(paramterName)) {
                        paramterIndex.put(paramterName, i);
                    }
                }
            }
        }

        // 获取方法参数类型

        Class<?>[] paramterTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < paramterTypes.length; i++) {
            Class<?> type = paramterTypes[i];
            if (type == HttpServletRequest.class ||
                    type == HttpServletResponse.class) {
                paramterIndex.put(type.getName(), i);
            }
        }

        // 获取请求参数
        Map<String, String[]> reqParamterMap = req.getParameterMap();

        Object[] paramterValues = new Object[paramterTypes.length];
        // 匹配参数，获取位置
        for (Map.Entry<String, String[]> param : reqParamterMap.entrySet()) {

            if (!paramterIndex.containsKey(param.getKey())) {
                continue;
            }
            Integer index = paramterIndex.get(param.getKey());

            String value = Arrays.toString(param.getValue())
                    .replaceAll("\\[|\\]", "").replaceAll("\\s", "");
            // todo 参数类型转换
            // 相应位置赋值
            paramterValues[index] = value;
        }

        String requestName = HttpServletRequest.class.getName();
        if (paramterIndex.containsKey(requestName)) {
            int index = paramterIndex.get(requestName);
            paramterValues[index] = req;
        }
        String respName = HttpServletResponse.class.getName();
        if (paramterIndex.containsKey(respName)) {
            int index = paramterIndex.get(respName);
            paramterValues[index] = resp;
        }

        // 调用方法
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramterValues);
        // 返回 modelAndView

        if (null == result) {
            return null;
        }

        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == V2ModelAndView.class;
        if (isModelAndView) {
            return (V2ModelAndView) result;
        }
        return null;
    }

}
