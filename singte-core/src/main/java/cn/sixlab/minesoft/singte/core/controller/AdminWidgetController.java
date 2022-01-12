package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StWidgetDao;
import cn.sixlab.minesoft.singte.core.models.StWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/widget")
public class AdminWidgetController extends BaseController {

    @Autowired
    private StWidgetDao widgetDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/widget/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StWidget> pageResult = widgetDao.selectWidgets(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/widget/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
        StWidget widget = widgetDao.selectById(id);
        widget.setStatus(status);
        widgetDao.save(widget);
        return ModelResp.success();
    }

}
