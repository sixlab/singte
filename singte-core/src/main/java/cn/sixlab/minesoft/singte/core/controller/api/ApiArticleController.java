package cn.sixlab.minesoft.singte.core.controller.api;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
@Api(tags = "文章", description = "/api/article 文章")
public class ApiArticleController extends BaseController {

    @Autowired
    private StArticleDao articleDao;

    @Autowired
    private ArticleService service;

    @GetMapping(value = "/list")
    @ApiOperation(value = "获取文章列表", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp list(
            @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam(name = "pageSize", value = "每页数量", required = true) @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageResult<StArticle> result = service.list(pageNum, pageSize);

        return ModelResp.success(result);
    }

    @GetMapping(value = "/content/{articleId}")
    @ApiOperation(value = "获取文章详情", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp content(
            @ApiParam(name = "articleId", value = "文章ID", required = true) @PathVariable String articleId
    ) {
        StArticle article = articleDao.selectByAlias(articleId);
        if (article == null) {
            article = articleDao.selectById(articleId);
        }

        if (null == article) {
            return ModelResp.error(StErr.NOT_EXIST, "文章不存在");
        } else {
            articleDao.addView(article.getId());

            return ModelResp.success(article);
        }
    }

    @ResponseBody
    @PostMapping(value = "/thumb")
    @ApiOperation(value = "点赞文章", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp thumb(
            @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam String articleId
    ) {
        StArticle article = articleDao.selectById(articleId);
        article.setThumbCount(article.getThumbCount() + 1);
        articleDao.save(article);
        return ModelResp.success(article.getThumbCount());
    }

    // @GetMapping(value = "/search")
    // @ApiOperation(value = "搜索文章", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    // public ModelResp search(ModelMap modelMap,
    //                      @ApiParam(name = "keyword", value = "关键词", required = true) @RequestParam String keyword,
    //                      @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam(defaultValue = "1") Integer pageNum,
    //                      @ApiParam(name = "pageSize", value = "每页数量", required = true) @RequestParam(defaultValue = "10") Integer pageSize
    // ) {
    //     service.listSearch(modelMap, keyword, pageNum, pageSize);
    //     return "list";
    // }
    //
    // @GetMapping(value = "/category/{category}")
    // @ApiOperation(value = "获取分类文章列表", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    // public ModelResp category(ModelMap modelMap,
    //                        @ApiParam(name = "category", value = "分类", required = true) @PathVariable String category,
    //                        @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam(defaultValue = "1") Integer pageNum,
    //                        @ApiParam(name = "pageSize", value = "每页数量", required = true) @RequestParam(defaultValue = "10") Integer pageSize
    // ) {
    //     service.listCategory(modelMap, category, pageNum, pageSize);
    //     return "list";
    // }
    //
    // @GetMapping(value = "/keyword/{keyword}")
    // @ApiOperation(value = "获取标签文章列表", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    // public ModelResp keyword(ModelMap modelMap,
    //                       @ApiParam(name = "keyword", value = "关键词", required = true) @PathVariable String keyword,
    //                       @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam(defaultValue = "1") Integer pageNum,
    //                       @ApiParam(name = "pageSize", value = "每页数量", required = true) @RequestParam(defaultValue = "10") Integer pageSize
    // ) {
    //     service.listKeyword(modelMap, keyword, pageNum, pageSize);
    //     return "list";
    // }
    //
    // @GetMapping(value = "/date/{date}")
    // @ApiOperation(value = "获取日期文章列表", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    // public ModelResp date(ModelMap modelMap,
    //                    @ApiParam(name = "date", value = "日期", required = true) @PathVariable String date,
    //                    @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam(defaultValue = "1") Integer pageNum,
    //                    @ApiParam(name = "pageSize", value = "每页数量", required = true) @RequestParam(defaultValue = "10") Integer pageSize
    // ) {
    //     service.listDate(modelMap, date, pageNum, pageSize);
    //     return "list";
    // }
}
