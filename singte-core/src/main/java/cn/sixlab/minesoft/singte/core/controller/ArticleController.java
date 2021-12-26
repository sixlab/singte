package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ArticleController extends BaseController {

    @Autowired
    private StArticleDao articleMapper;

    @Autowired
    private ArticleService service;

    @GetMapping(value = "/article/{articleId}")
    public String article(ModelMap modelMap, @PathVariable String articleId) {

        StArticle article = articleMapper.selectByAlias(articleId);
        if (article == null) {
            article = articleMapper.selectById(articleId);
        }

        if (null == article) {
            return "redirect:/404";
        } else {
            articleMapper.addView(article.getId());

            modelMap.put("article", article);
            return "article";
        }
    }

    @GetMapping(value = "/list")
    public String list(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        service.listList(modelMap, pageNum, pageSize);
        return "list";
    }

    @GetMapping(value = "/search")
    public String search(ModelMap modelMap, String keyword, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        service.listSearch(modelMap, keyword, pageNum, pageSize);
        return "list";
    }

    @GetMapping(value = "/category/{category}")
    public String category(ModelMap modelMap, @PathVariable String category, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        service.listCategory(modelMap, category, pageNum, pageSize);
        return "list";
    }

    @GetMapping(value = "/keyword/{keyword}")
    public String keyword(ModelMap modelMap, @PathVariable String keyword, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        service.listKeyword(modelMap, keyword, pageNum, pageSize);
        return "list";
    }

    @GetMapping(value = "/date/{date}")
    public String date(ModelMap modelMap, @PathVariable String date, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        service.listDate(modelMap, date, pageNum, pageSize);
        return "list";
    }
}
