package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.init.FtlInit;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private FtlInit ftlInit;

    @GetMapping(value = "/config")
    public String config(ModelMap modelMap) {
        modelMap.put("configs", StConst.ST_CONFIGS);
        return "admin/config";
    }

    @GetMapping(value = "/submitConfig")
    public String submitConfig() {
        return "admin/config";
    }

    @ResponseBody
    @GetMapping(value = "/reloadConfig")
    public ModelResp reloadConfig(ModelMap modelMap) {
        ftlInit.configFtl();
        return ModelResp.success();
    }

}
