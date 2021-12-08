package cn.sixlab.minesoft.singte.core.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Locale;

@Component
public class FreemarkerUtils {

    @Autowired
    private FreeMarkerViewResolver freeMarkerViewResolver;

    public void clearCache() {
        freeMarkerViewResolver.clearCache();
    }

    public void clearCache(String viewName) {
        freeMarkerViewResolver.removeFromCache(viewName, Locale.getDefault());
    }

    public void clearCache(String viewName, Locale locale) {
        freeMarkerViewResolver.removeFromCache(viewName, locale);
    }
}
