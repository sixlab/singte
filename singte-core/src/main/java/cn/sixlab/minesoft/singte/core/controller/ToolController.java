package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.*;
import cn.sixlab.minesoft.singte.core.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tool")
public class ToolController extends BaseController {

    @Autowired
    private SteToolCategoryDao toolCategoryDao;

    @Autowired
    private SteToolItemDao toolItemDao;

    @GetMapping(value = "/list")
    public String listSet(ModelMap modelMap) {

        modelMap.put("result", toolCategoryDao.list());

        return "tool/list";
    }

    @GetMapping(value = "/category/{categoryId}")
    public String listCategory(ModelMap modelMap, @PathVariable String categoryId) {
        SteToolCategory toolCategory = toolCategoryDao.selectById(categoryId);

        String category = toolCategory.getCategory();

        modelMap.put("title", category);
        modelMap.put("result", toolItemDao.listByCategory(category));

        return "tool/category";
    }

    @GetMapping(value = "/item/{toolId}")
    public String item(ModelMap modelMap, @PathVariable String toolId) {
        SteToolItem toolItem = toolItemDao.selectById(toolId);

        SteToolCategory toolCategory = toolCategoryDao.selectByName(toolItem.getCategory());

        modelMap.put("categoryId", toolCategory.getId());

        modelMap.put("title", toolItem.getToolName());
        modelMap.put("toolItem", toolItem);

        return "tool/item";
    }

    @ResponseBody
    @PostMapping(value = "/thumb")
    public ModelResp thumb(String toolId) {
        SteToolItem toolItem = toolItemDao.selectById(toolId);
        toolItem.setThumbCount(toolItem.getThumbCount() + 1);
        toolItemDao.save(toolItem);
        return ModelResp.success(toolItem.getThumbCount());
    }

}
