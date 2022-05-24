package cn.sixlab.minesoft.singte.core.controller.common;

import cn.hutool.extra.spring.SpringUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDomain;
import cn.sixlab.minesoft.singte.core.models.StDomain;
import cn.sixlab.minesoft.singte.core.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonController {

    @Autowired
    private DomainService domainService;

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(ModelMap modelMap) {
        BaseDomain baseDomain = domain();

        if (baseDomain != null) {
            return baseDomain.index(modelMap);
        }
        return "index";
    }

    @GetMapping(value = {"/test"})
    public String test(ModelMap modelMap) {
        BaseDomain baseDomain = domain();

        if (baseDomain != null) {
            return baseDomain.test(modelMap);
        }
        return "index";
    }

    private BaseDomain domain(){
        BaseDomain baseDomain = null;

        StDomain stDomain = domainService.activeDomain();
        if (stDomain != null) {
            try {
                baseDomain = SpringUtil.getBean(stDomain.getDomainBean());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return baseDomain;
    }

}
