package cn.sixlab.minesoft.singte.core.common.directive;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ConfigDirective implements TemplateDirectiveModel {

    @Autowired
    private ConfigUtils configUtils;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String config = "";
        String key = MapUtil.getStr(params, "key");
        if (StrUtil.isNotEmpty(key)) {
            String val = configUtils.getConfig(key);
            if (StrUtil.isNotEmpty(val)) {
                config = val;
            }
        }
        if(StrUtil.isEmpty(config)){
            config = MapUtil.getStr(params, "default");
        }

        env.getOut().append(config);
    }
}
