package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/{category}")
    public String category(ModelMap modelMap, @PathVariable String category) {
        articleService.listCategory(modelMap, category, 1, 10);
        return "list";
    }

    @GetMapping(value = "/{category}/{pageNum}")
    public String category(ModelMap modelMap, @PathVariable String category, @PathVariable Integer pageNum) {
        articleService.listCategory(modelMap, category, pageNum, 10);
        return "list";
    }

    @GetMapping(value = "/{category}/{pageNum}/{pageSize}")
    public String category(ModelMap modelMap, @PathVariable String category, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        articleService.listCategory(modelMap, category, pageNum, pageSize);
        return "list";
    }

}
