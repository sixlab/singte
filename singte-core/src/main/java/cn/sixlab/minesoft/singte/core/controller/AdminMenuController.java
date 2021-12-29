package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/admin")
public class AdminMenuController extends BaseController {

    @GetMapping(value = "/menus")
    public String menus() {
        return "auth/admin/menus";
    }

}
