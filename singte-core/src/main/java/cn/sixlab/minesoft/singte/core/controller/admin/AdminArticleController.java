package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
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
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StArticle> pageResult = articleDao.queryArticle(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/article/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitArticle")
    public ModelResp submitArticle(StArticle params) {
        StArticle nextInfo;
        StArticle checkExist = articleDao.selectByAlias(params.getAlias());

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = articleDao.selectById(params.getId());

            if (null == nextInfo) {
                return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
            }

            if (null != checkExist && !params.getId().equals(checkExist.getId())) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo.setUpdateTime(new Date());
        } else {
            if (null != checkExist) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo = new StArticle();
            nextInfo.setViewCount(0);
            nextInfo.setThumbCount(0);
            nextInfo.setStatus(StConst.ST_PUBLISH_DID);
            nextInfo.setPublishTime(new Date());
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setAlias(params.getAlias());
        nextInfo.setSourceUrl(params.getSourceUrl());
        nextInfo.setTitle(params.getTitle());
        nextInfo.setAuthor(params.getAuthor());
        nextInfo.setKeywords(params.getKeywords());
        nextInfo.setSummary(params.getSummary());
        nextInfo.setCategory(params.getCategory());
        nextInfo.setContent(params.getContent());

        articleDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
        StArticle record = articleDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        articleDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        StArticle record = articleDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        articleDao.deleteById(id);

        return ModelResp.success();
    }
}
