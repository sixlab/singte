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
@RequestMapping("/")
public class CategoryController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/category/{categoryId}")
    public String category(ModelMap modelMap, @PathVariable Integer categoryId) {
        boolean exists = articleService.listCategory(modelMap, categoryId, 1, 10);
        if (exists) {
            return "list";
        } else {
            return "redirect:/404";
        }
    }

    @GetMapping(value = "/category/{categoryId}/{pageNum}")
    public String category(ModelMap modelMap, @PathVariable Integer categoryId, @PathVariable Integer pageNum) {
        boolean exists = articleService.listCategory(modelMap, categoryId, pageNum, 10);
        if (exists) {
            return "list";
        } else {
            return "redirect:/404";
        }
    }

    @GetMapping(value = "/category/{categoryId}/{pageNum}/{pageSize}")
    public String category(ModelMap modelMap, @PathVariable Integer categoryId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        boolean exists = articleService.listCategory(modelMap, categoryId, pageNum, pageSize);
        if (exists) {
            return "list";
        } else {
            return "redirect:/404";
        }
    }

}
