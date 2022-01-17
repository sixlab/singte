package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/ancient/category")
public class AdminAncientCategoryController extends BaseController {

    @Autowired
    private SteAncientCategoryDao ancientCategoryDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/ancient/categoryList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<SteAncientCategory> pageResult = ancientCategoryDao.selectAncientCategory(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/categoryListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitCategory")
    public ModelResp submitCategory(SteAncientCategory steAncientCategory) {
        steAncientCategory.setCount(0);
        steAncientCategory.setCreateTime(new Date());
        ancientCategoryDao.save(steAncientCategory);
        return ModelResp.success();
    }

}
