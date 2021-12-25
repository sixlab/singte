package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
        String key = MapUtils.getString(params, "key");
        if (StringUtils.isNotEmpty(key)) {
            String val = configUtils.getConfig(key);
            if (StringUtils.isNotEmpty(val)) {
                config = val;
            }
        }
        if(StringUtils.isEmpty(key)){
            config = MapUtils.getString(params, "default");
        }

        env.getOut().append(config);
    }
}
