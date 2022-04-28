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

        StDomain stDomain = domainService.activeDomain();

        if (stDomain != null) {
            BaseDomain baseDomain = null;
            try {
                baseDomain = SpringUtil.getBean(stDomain.getDomainBean());
            }catch (Exception e){
                e.printStackTrace();
            }
            if (null != baseDomain) {
                return baseDomain.index(modelMap);
            }

        }
        return "index";
    }

}
