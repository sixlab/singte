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
@RequestMapping("/keyword")
public class KeywordController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/{keyword}")
    public String keyword(ModelMap modelMap, @PathVariable String keyword) {
        articleService.listKeyword(modelMap, keyword, 1, 10);
        return "list";
    }

    @GetMapping(value = "/{keyword}/{pageNum}")
    public String keyword(ModelMap modelMap, @PathVariable String keyword, @PathVariable Integer pageNum) {
        articleService.listKeyword(modelMap, keyword, pageNum, 10);
        return "list";
    }

    @GetMapping(value = "/{keyword}/{pageNum}/{pageSize}")
    public String keyword(ModelMap modelMap, @PathVariable String keyword, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        articleService.listKeyword(modelMap, keyword, pageNum, pageSize);
        return "list";
    }

}
