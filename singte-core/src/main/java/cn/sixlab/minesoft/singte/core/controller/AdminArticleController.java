package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController extends BaseController {

    @Autowired
    private StArticleDao articleDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/article/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<StArticle> pageResult = articleDao.selectArticles(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/article/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitArticle")
    public ModelResp submitArticle(StArticle stArticle) {
        stArticle.setViewCount(0);
        stArticle.setThumbCount(0);
        stArticle.setPublishStatus(StConst.ST_PUBLISH_DID);
        stArticle.setPublishTime(new Date());
        stArticle.setCreateTime(new Date());
        articleDao.save(stArticle);
        return ModelResp.success();
    }

}
