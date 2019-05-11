package dev.frames.spring.web.servlet;

import java.util.Map;

public class V2ModelAndView {

    private String view;

    /**
     * Model Map
     */
    private Map<String, ?> model;

    public V2ModelAndView(String view) {
        this.view = view;
    }

    public V2ModelAndView(String view, Map<String, ?> model) {
        this.view = view;
        this.model = model;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
