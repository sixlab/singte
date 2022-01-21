package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteToolCategoryDao;
import cn.sixlab.minesoft.singte.core.models.SteToolCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/tool/category")
public class AdminToolCategoryController extends BaseController {

    @Autowired
    private SteToolCategoryDao toolCategoryDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/tool/categoryList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<SteToolCategory> pageResult = toolCategoryDao.selectCategories(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/tool/categoryListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitCategory")
    public ModelResp submitCategory(SteToolCategory steToolCategory) {
        steToolCategory.setCount(0);
        steToolCategory.setCreateTime(new Date());
        toolCategoryDao.save(steToolCategory);
        return ModelResp.success();
    }

}
