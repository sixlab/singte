package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteToolItemDao;
import cn.sixlab.minesoft.singte.core.models.SteToolItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/tool")
public class AdminToolController extends BaseController {

    @Autowired
    private SteToolItemDao toolItemDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/tool/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<SteToolItem> pageResult = toolItemDao.selectTools(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/tool/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitTool")
    public ModelResp submitSet(SteToolItem steToolItem) {
        steToolItem.setViewCount(0);
        steToolItem.setThumbCount(0);
        steToolItem.setCreateTime(new Date());
        toolItemDao.save(steToolItem);
        return ModelResp.success();
    }

}
