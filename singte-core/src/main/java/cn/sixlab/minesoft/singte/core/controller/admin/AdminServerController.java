package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/admin/server")
public class AdminServerController extends BaseController {
    private static final Date startTime = new Date();

    @Autowired
    private ConfigUtils configUtils;

    @GetMapping(value = "/info")
    public String info(ModelMap modelMap) {
        modelMap.put("startTime", startTime);

        return "admin/server/info";
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ModelResp update() {
        String path = configUtils.getConfig(StConst.SERVER_SH_PATH);

        Process exec;
        try {
            exec = Runtime.getRuntime().exec("update.sh", null, new File(path));
            exec.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            return ModelResp.error(StErr.UNKNOWN, "脚本运行异常");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ModelResp.error(StErr.UNKNOWN, "脚本运行中断");
        }

        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/reboot")
    public ModelResp reboot() {
        String path = configUtils.getConfig(StConst.SERVER_SH_PATH);

        Process exec;
        try {
            exec = Runtime.getRuntime().exec("kill.sh", null, new File(path));
            exec.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            return ModelResp.error(StErr.UNKNOWN, "脚本运行异常");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ModelResp.error(StErr.UNKNOWN, "脚本运行中断");
        }

        return ModelResp.success();
    }

}
