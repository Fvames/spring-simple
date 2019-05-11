package dev.frames.spring.web.servlet;

import java.util.Map;

public class V2ModelAndView {

    private Object view;

    /**
     * Model Map
     */
    private Map<String, ?> model;

    public V2ModelAndView(Object view) {
        this.view = view;
    }

    public V2ModelAndView(Object view, Map<String, ?> model) {
        this.view = view;
        this.model = model;
    }

    public Object getView() {
        return view;
    }

    public void setView(Object view) {
        this.view = view;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
