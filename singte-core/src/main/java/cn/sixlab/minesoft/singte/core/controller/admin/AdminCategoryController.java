package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StCategoryDao;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController extends BaseController {

    @Autowired
    private StCategoryDao categoryDao;

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/reload")
    public ModelResp reload() {
        int categorySize = articleService.countCategory();
        return ModelResp.success(categorySize);
    }

    @GetMapping(value = "/list")
    public String list() {
        return "admin/category/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StCategory> pageResult = categoryDao.selectCategories(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/category/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitCategory")
    public ModelResp submitCategory(StCategory params) {
        StCategory nextInfo;

        StCategory checkExist = categoryDao.selectByCategory(params.getCategory());

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = categoryDao.selectById(params.getId());

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

            nextInfo = new StCategory();
            nextInfo.setCount(0);
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setCategory(params.getCategory());
        nextInfo.setWeight(params.getWeight());
        nextInfo.setIntro(params.getIntro());

        categoryDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
        StCategory record = categoryDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        categoryDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        StCategory record = categoryDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        categoryDao.deleteById(id);

        return ModelResp.success();
    }
}
