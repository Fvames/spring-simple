package dev.fames.mvc.v2.webmvc;

import java.util.Map;

public class V2ModelAndView {
    private String viewName;
    private Map<String, ?> model;

    public V2ModelAndView() {
    }

    public V2ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public V2ModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
