package dev.fames.mvc.v2.webmvc;

import java.io.File;
import java.util.Locale;

/**
 * 变量替换
 */
public class V2ViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";

    private File templateRootDir;
    private String viewName;

    public V2ViewResolver(String templateRoot) {
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        this.templateRootDir = new File(templateRootPath);
    }

    public V2View resolveViewName(String viewName, Locale locale) throws Exception {
        this.viewName = viewName;
        if (null == viewName || "".equals(viewName.trim())) {
            return null;
        }

        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFIX);
        File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));
        return new V2View(templateFile);
    }

    public String getViewName() {
        return viewName;
    }
}
