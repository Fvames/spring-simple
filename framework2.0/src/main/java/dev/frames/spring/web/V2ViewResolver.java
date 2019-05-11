package dev.frames.spring.web;

import java.io.File;

public class V2ViewResolver {

    private static final String DEFULT_TEMPLATE_SUFFIX = ".html";
    private File templateRootDir;
    private String viewName;

    public V2ViewResolver(File file) {
        //this.templateRootDir = new File(this.getClass().getClassLoader().getResource(filePath).getFile());
        this.templateRootDir = file;
    }

    public V2View resolveViewName(String viewName) {
        this.viewName = viewName;

        if (null == viewName || "".equals(viewName)) {
            return null;
        }

        viewName = viewName.endsWith(DEFULT_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFULT_TEMPLATE_SUFFIX);
        String filePath = (templateRootDir.getPath() + File.separator + viewName).replaceAll("/+", "");
        return new V2View(new File(filePath));
    }


}
