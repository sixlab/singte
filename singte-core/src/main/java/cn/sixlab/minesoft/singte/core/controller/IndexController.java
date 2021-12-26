package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.I18nUtils;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        service.listParam(modelMap, 1, 10, "list", "/list");
        modelMap.put("title", configUtils.getConfig("st_site_name"));
        return "list";
    }

    @GetMapping(value = "/list/{pageNum}")
    public String list(ModelMap modelMap, @PathVariable Integer pageNum) {
        service.listParam(modelMap, pageNum, 10, "list", "/list");
        modelMap.put("title", I18nUtils.get("title.list"));
        return "list";
    }

    @GetMapping(value = "/list/{pageNum}/{pageSize}")
    public String list(ModelMap modelMap, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        service.listParam(modelMap, pageNum, pageSize, "list", "/list");
        modelMap.put("title", I18nUtils.get("title.list"));
        return "list";
    }
}
