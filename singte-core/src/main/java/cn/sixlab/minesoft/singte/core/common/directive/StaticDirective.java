package cn.sixlab.minesoft.singte.core.common.directive;

import cn.hutool.core.map.MapUtil;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class StaticDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {


        String type = MapUtil.getStr(params, "type", "js");
        String src = MapUtil.getStr(params, "src");

        if ("css".equals(type)) {
            String prefix = MapUtil.getStr(params, "prefix","/static/css/");
            env.getOut().append("<link rel='stylesheet' type='text/css' href='" + prefix + src + "?_t=" + StConst.DEPLOY_DATE + "'/>");
        } else {
            String prefix = MapUtil.getStr(params, "prefix","/static/js/");
            env.getOut().append("<script type='text/javascript' src='" + prefix + src + "?_t=" + StConst.DEPLOY_DATE + "'></script>");
        }
    }
}
