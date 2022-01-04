package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StMenuDao;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminMenuController extends BaseController {

    @Autowired
    private StMenuDao menuDao;

    @GetMapping(value = "/menus")
    public String menus() {
        return "admin/menus";
    }

    @ResponseBody
    @PostMapping(value = "/selectMenus")
    public ModelResp selectMenus(String keyword, String status, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<StMenu> menuPageResult = menuDao.selectMenus(keyword, status, pageNum, pageSize);

        return ModelResp.success(menuPageResult);
    }

}
