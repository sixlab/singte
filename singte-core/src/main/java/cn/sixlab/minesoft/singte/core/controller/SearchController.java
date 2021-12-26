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
@RequestMapping("/search")
public class SearchController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = {"", "/"})
    public String search(ModelMap modelMap, String keyword) {
        articleService.listSearch(modelMap, keyword, 1, 10);
        return "list";
    }

    @GetMapping(value = "/{pageNum}")
    public String search(ModelMap modelMap, String keyword, @PathVariable Integer pageNum) {
        articleService.listSearch(modelMap, keyword, pageNum, 10);
        return "list";
    }

    @GetMapping(value = "/{pageNum}/{pageSize}")
    public String search(ModelMap modelMap, String keyword, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        articleService.listSearch(modelMap, keyword, pageNum, pageSize);
        return "list";
    }

}
