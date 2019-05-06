package dev.fames.mvc.v2.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class V2HandlerMapping {

    private Object controller;
    private Method method;
    private Pattern pattern;

    public V2HandlerMapping() {
    }

    public V2HandlerMapping(Pattern pattern, Object controller, Method method) {
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
