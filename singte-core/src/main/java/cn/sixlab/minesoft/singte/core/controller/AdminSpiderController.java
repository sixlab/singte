package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StSpiderDao;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/spider")
public class AdminSpiderController extends BaseController {

    @Autowired
    private StSpiderDao spiderDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/spider/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<StSpider> pageResult = spiderDao.selectSpiders(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/spider/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitSpider")
    public ModelResp submitSpider(StSpider stSpider) {
        stSpider.setSpiderStatus(StConst.YES);
        stSpider.setCreateTime(new Date());
        spiderDao.save(stSpider);
        return ModelResp.success();
    }

}
