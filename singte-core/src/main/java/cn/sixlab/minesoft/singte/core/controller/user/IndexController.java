package cn.sixlab.minesoft.singte.core.controller.user;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.dao.StPageDao;
import cn.sixlab.minesoft.singte.core.models.StPage;
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
    private StPageDao pageDao;

    @Autowired
    private ArticleService service;

    @Autowired
    private ConfigUtils configUtils;

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(ModelMap modelMap) {
        service.listList(modelMap, 1, 10);
        modelMap.put("title", configUtils.getConfig("st_site_name"));
        return "index";
    }

    @GetMapping(value = "/about")
    public String article(ModelMap modelMap) {
        StPage page = pageDao.selectByAlias("about");

        if (null == page) {
            return "redirect:/404";
        } else {
            pageDao.addView(page.getId());

            modelMap.put("page", page);
            return "page";
        }
    }

}
