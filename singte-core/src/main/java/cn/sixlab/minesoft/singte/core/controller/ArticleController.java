package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private StArticleDao articleMapper;

    @GetMapping(value = "/{articleId}")
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

}
