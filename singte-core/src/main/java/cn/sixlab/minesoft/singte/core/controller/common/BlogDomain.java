package cn.sixlab.minesoft.singte.core.controller.common;

import cn.sixlab.minesoft.singte.core.common.config.BaseDomain;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class BlogDomain implements BaseDomain {

    @Autowired
    private ArticleService service;

    @Autowired
    private ConfigUtils configUtils;

    @Override
    public String index(ModelMap modelMap) {
        service.listList(modelMap, 1, 10);
        modelMap.put("title", configUtils.getConfig("st_site_name"));
        return "/index";
    }

    @Override
    public String test(ModelMap modelMap) {
        return "/test";
    }

}
