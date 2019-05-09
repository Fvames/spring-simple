package dev.frames.spring.web.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 请求映射
 *
 * @version 2019/5/9 16:46
 */

public class V2HandlerMapping {
    private Object controller;
    private Method method;
    private Pattern pattern;

    public V2HandlerMapping(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
