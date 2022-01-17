package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
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
    public ModelResp submitCategory(StCategory stCategory) {
        stCategory.setCount(0);
        stCategory.setCreateTime(new Date());
        categoryDao.save(stCategory);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/resetCategory")
    public ModelResp resetCategory() {
        int categorySize = articleService.countCategory();
        return ModelResp.success(categorySize);
    }

}
