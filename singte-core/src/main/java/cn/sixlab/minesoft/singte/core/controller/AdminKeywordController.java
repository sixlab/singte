package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StKeywordDao;
import cn.sixlab.minesoft.singte.core.models.StKeyword;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/keyword")
public class AdminKeywordController extends BaseController {

    @Autowired
    private StKeywordDao keywordDao;

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/keyword/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StKeyword> pageResult = keywordDao.selectKeywords(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/keyword/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitKeyword")
    public ModelResp submitKeyword(StKeyword stKeyword) {
        stKeyword.setArticleCount(0);
        stKeyword.setCreateTime(new Date());
        keywordDao.save(stKeyword);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/resetKeyword")
    public ModelResp resetKeyword() {
        int keywordSize = articleService.countKeyword();
        return ModelResp.success(keywordSize);
    }
}
