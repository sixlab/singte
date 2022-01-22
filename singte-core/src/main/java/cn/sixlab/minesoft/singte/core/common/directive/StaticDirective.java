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
        String prefix = MapUtil.getStr(params, "prefix", "/static/");

        switch (type) {
            case "validate":
                env.getOut().append("<script type='text/javascript' src='/static/plugins/jquery-validation/jquery.validate.min.js'></script>");
                env.getOut().append("<script type='text/javascript' src='/static/plugins/jquery-validation/additional-methods.min.js'></script>");
                break;
            case "css":
                env.getOut().append("<link rel='stylesheet' type='text/css' href='" + prefix + "css/" + src + "?_t=" + StConst.DEPLOY_DATE + "'/>");
                break;
            case "js":
            default:
                env.getOut().append("<script type='text/javascript' src='" + prefix + "js/" + src + "?_t=" + StConst.DEPLOY_DATE + "'></script>");
                break;
        }
    }
}
