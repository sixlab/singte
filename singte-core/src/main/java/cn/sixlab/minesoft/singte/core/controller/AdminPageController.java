package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StPageDao;
import cn.sixlab.minesoft.singte.core.models.StPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/page")
public class AdminPageController extends BaseController {

    @Autowired
    private StPageDao pageDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/page/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<StPage> pageResult = pageDao.selectPages(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/page/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitPage")
    public ModelResp submitPage(StPage stPage) {
        stPage.setViewCount(0);
        stPage.setPublishStatus(StConst.ST_PUBLISH_DID);
        stPage.setPublishTime(new Date());
        stPage.setCreateTime(new Date());
        pageDao.save(stPage);
        return ModelResp.success();
    }

}
