package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
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
    public ModelResp submitMenu(StMenu params) {
        StMenu nextInfo;

        StMenu checkExist = menuDao.selectByMenuCode(params.getMenuCode());

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = menuDao.selectById(params.getId());

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

            nextInfo = new StMenu();
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }


        if (null == params.getFolderMenu()) {
            nextInfo.setFolderMenu(false);
        }else{
            nextInfo.setFolderMenu(params.getFolderMenu());
        }
        nextInfo.setMenuCode(params.getMenuCode());
        nextInfo.setMenuLink(params.getMenuLink());
        nextInfo.setMenuIcon(params.getMenuIcon());
        nextInfo.setMenuGroup(params.getMenuGroup());
        nextInfo.setWeight(params.getWeight());
        nextInfo.setIntro(params.getIntro());

        menuDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
        StMenu record = menuDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        menuDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        StMenu record = menuDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        menuDao.deleteById(id);

        return ModelResp.success();
    }
}
