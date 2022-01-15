package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.CtxHolder;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private StArticleDao articleDao;

    @Autowired
    private ArticleService service;

    @GetMapping(value = "/list")
    public String list(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<StArticle> result = service.list(pageNum, pageSize);

        modelMap.put("result", result);

        return "article/list";
    }

    @GetMapping(value = "/content/{articleId}")
    public String content(ModelMap modelMap, @PathVariable String articleId) {

        StArticle article = articleDao.selectByAlias(articleId);
        if (article == null) {
            article = articleDao.selectById(articleId);
        }

        if (null == article) {
            return "redirect:/404";
        } else {
            articleDao.addView(article.getId());

            modelMap.put("article", article);
            return "article/content";
        }
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

    @ResponseBody
    @PostMapping(value = "/thumb")
    public ModelResp thumb(String articleId) {
        StArticle article = articleDao.selectById(articleId);
        article.setThumbCount(article.getThumbCount()+1);
        articleDao.save(article);
        return ModelResp.success(article.getThumbCount());
    }

    @ResponseBody
    @GetMapping(value = "/initTest")
    public String initTest() {
        for (int i = 1; i < 100; i++) {
            SteAncientBook ancientBook = new SteAncientBook();
            ancientBook.setAncientSet("经部");
            ancientBook.setAncientCategory("易类");
            ancientBook.setBookName("测试文章"+i);
            ancientBook.setAuthor("派大星");
            ancientBook.setWeight(i+2);
            ancientBook.setIntro("简介" + i);
            ancientBook.setCreateTime(new Date());

            CtxHolder.getBean(SteAncientBookDao.class).save(ancientBook);

            SteAncientSection ancientSection = new SteAncientSection();

            ancientSection.setAncientSet("经部");
            ancientSection.setAncientCategory("易类");
            ancientSection.setBookName("测试文章1");
            ancientSection.setSectionName("卷"+i);
            ancientSection.setAuthor("派大星");
            ancientSection.setContentHtml("<div>1</div><div>2</div><div>3</div>");
            ancientSection.setContentText("1 2 3");
            ancientSection.setWeight(i+4);
            ancientSection.setViewCount(0);
            ancientSection.setThumbCount(0);
            ancientSection.setIntro("简介" + i);
            ancientSection.setCreateTime(new Date());

            CtxHolder.getBean(SteAncientSectionDao.class).save(ancientSection);
        }

        return "success";
    }
}
