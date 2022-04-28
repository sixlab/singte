package cn.sixlab.minesoft.singte.core.controller.common;

import cn.sixlab.minesoft.singte.core.common.config.BaseDomain;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class StackDomain implements BaseDomain {

    @Override
    public String index(ModelMap modelMap) {
        return "/index";
    }

}
