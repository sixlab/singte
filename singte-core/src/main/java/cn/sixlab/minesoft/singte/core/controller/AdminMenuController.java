package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StMenuDao;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminMenuController extends BaseController {

    @Autowired
    private StMenuDao menuDao;

    @GetMapping(value = "/menus")
    public String menus() {
        return "admin/menus";
    }

    @PostMapping(value = "/menusData")
    public String menusData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<StMenu> menuPageResult = menuDao.selectMenus(keyword, status, pageNum, pageSize);

        modelMap.put("result", menuPageResult);

        return "admin/menusData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitMenu")
    public ModelResp submitMenu(StMenu stMenu) {
        if (null == stMenu.getFolderMenu()) {
            stMenu.setFolderMenu(false);
        }
        stMenu.setStatus(StConst.YES);
        stMenu.setCreateTime(new Date());
        menuDao.save(stMenu);
        return ModelResp.success();
    }

}
