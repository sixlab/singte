package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private ArticleService service;

    @Autowired
    private ConfigUtils configUtils;

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(ModelMap modelMap) {
        service.listList(modelMap, 1, 10);
        modelMap.put("title", configUtils.getConfig("st_site_name"));
        return "list";
    }

}
