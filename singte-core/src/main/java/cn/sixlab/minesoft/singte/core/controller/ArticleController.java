package cn.sixlab.minesoft.singte.core.controller;

import cn.hutool.core.util.RandomUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/")
public class ArticleController extends BaseController {

    @Autowired
    private StArticleDao articleDao;

    @Autowired
    private ArticleService service;

    @GetMapping(value = "/articles")
    public String articles(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<StArticle> result = service.list(pageNum, pageSize);

        modelMap.put("result", result);

        return "articles";
    }

    @GetMapping(value = "/article/{articleId}")
    public String article(ModelMap modelMap, @PathVariable String articleId) {

        StArticle article = articleDao.selectByAlias(articleId);
        if (article == null) {
            article = articleDao.selectById(articleId);
        }

        if (null == article) {
            return "redirect:/404";
        } else {
            articleDao.addView(article.getId());

            modelMap.put("article", article);
            return "article";
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
    @GetMapping(value = "/initTest")
    public String initTest() {
        for (int i = 100; i < 200; i++) {
            StArticle stArticle = new StArticle();
            stArticle.setAlias("wz" + i);
            stArticle.setSourceUrl("http://sixlab.cn/" + i);
            stArticle.setTitle("第 " + i + " 次修改代码，来一篇文章试一下");
            stArticle.setAuthor("张三" + i % 5);
            Set<String> set = new HashSet<>();
            set.add("词" + i % 3);
            set.add("词" + i % 5);
            set.add("词" + i % 7);
            set.add("关键" + i % 4);
            set.add("关键" + i % 10);

            stArticle.setKeywords(new ArrayList<>(set));
            stArticle.setSummary("投资最好的时间是当下。只有站在现在看历史，才知道哪里是高点，哪里是低点。低买高卖是无数人追求的操作方式，难在我们无法判断当下的位置。可能现在是高点，一段时间后还有更高点；可能现在是低点，一段时间后有更低点。只有克服内心的喜悦和恐惧，大胆的在当下进行投资才能取得很好的收益。");
            stArticle.setCategory("分类" + i % 6);
            stArticle.setViewCount(RandomUtil.randomInt(1000, 2000));
            stArticle.setThumbCount(RandomUtil.randomInt(1000));
            stArticle.setPublishStatus("1");
            stArticle.setPublishTime(new Date());
            stArticle.setContent("<p>指数介绍<br>\n" +
                    "恒生中国企业指数，简称国企指数或H股（因香港英文——HongKong首字母，而称得名H股）。<br>\n" +
                    "代码HSCEI(The Hang Seng China Enterprises Index) 。<br>\n" +
                    "指注册地在内地、上市地在香港的中资企业股票。<br>\n" +
                    "H股为了反应内地企业在港交所的表现，选取市值最大、流通性最好的50家内地企业而组成。</p>\n" +
                    "<p>指数优缺点<br>\n" +
                    "H股的优点<br>\n" +
                    "港股比较成熟，企业的定价较为公平合理。<br>\n" +
                    "港交所以外资为主，资金比较持久和稳定。<br>\n" +
                    "H股的缺点<br>\n" +
                    "港交所国际化程度高，一有大的事件就会跟着波动。比如欧美跌港股跟跌，A股跌港股还跟跌。然而H股公司主营业务在内地，基本面没有变化，经常被误杀。</p>\n" +
                    "<p>指数筛选<br>\n" +
                    "基本信息对比</p>");
            stArticle.setUpdateTime(new Date());
            stArticle.setCreateTime(new Date());

            articleDao.save(stArticle);
        }

        return "";
    }
}
