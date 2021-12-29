package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class StaticDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String type = MapUtils.getString(params, "type", "js");
        String src = MapUtils.getString(params, "src");

        if ("css".equals(type)) {
            env.getOut().append("<link rel='stylesheet' type='text/css' href='/static/css/" + src + "?_t=" + StConst.DEPLOY_DATE + "'/>");
        } else {
            env.getOut().append("<script type='text/javascript' src='/static/js/" + src + "?_t=" + StConst.DEPLOY_DATE + "'></script>");
        }
    }
}
