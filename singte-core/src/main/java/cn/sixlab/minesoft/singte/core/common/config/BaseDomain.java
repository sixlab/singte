package cn.sixlab.minesoft.singte.core.common.config;

import org.springframework.ui.ModelMap;

public interface BaseDomain {
    default String index(ModelMap modelMap){
        return "index";
    }
}
