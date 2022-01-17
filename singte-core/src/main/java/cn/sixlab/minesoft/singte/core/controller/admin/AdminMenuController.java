package cn.sixlab.minesoft.singte.core.controller.admin;

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
@RequestMapping("/admin/menu")
public class AdminMenuController extends BaseController {

    @Autowired
    private StMenuDao menuDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/menu/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StMenu> menuPageResult = menuDao.selectMenus(keyword, status, pageNum, pageSize);

        modelMap.put("result", menuPageResult);

        return "admin/menu/listData";
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