package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.service.AdminServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/admin/server")
public class AdminServerController extends BaseController {
    private static final Date startTime = new Date();

    @Autowired
    private AdminServerService adminServerService;

    @GetMapping(value = "/info")
    public String info(ModelMap modelMap) {
        modelMap.put("startTime", startTime);

        return "admin/server/info";
    }

    @ResponseBody
    @PostMapping(value = "/info")
    public ModelResp info() {
        ModelResp resp = ModelResp.success();
        resp.put("startTime", startTime);
        resp.put("newServer", adminServerService.isNewServer());
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ModelResp update() {
        adminServerService.runShell("update.sh");
        return adminServerService.runShell("kill.sh");
    }

    @ResponseBody
    @RequestMapping(value = "/reboot")
    public ModelResp reboot() {
        return adminServerService.runShell("kill.sh");
    }

}
