package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StConfigDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/config")
public class AdminConfigController extends BaseController {

    @Autowired
    private StConfigDao configDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/config/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StConfig> pageResult = configDao.selectConfigs(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/config/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitConfig")
    public ModelResp submitConfig(StConfig stConfig) {
        stConfig.setStatus(StConst.YES);
        stConfig.setCreateTime(new Date());
        configDao.save(stConfig);
        return ModelResp.success();
    }

}
