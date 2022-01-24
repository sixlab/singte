package cn.sixlab.minesoft.singte.core.common.directive;

import cn.hutool.core.map.MapUtil;
import cn.sixlab.minesoft.singte.core.common.utils.I18nUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.service.LangService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class StaticDirective implements TemplateDirectiveModel {

    @Autowired
    private LangService service;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String type = MapUtil.getStr(params, "type", "js");
        String src = MapUtil.getStr(params, "src");
        String prefix = MapUtil.getStr(params, "prefix", "/static/");

        switch (type) {
            case "i18n":
                Object keys = params.get("keys");
                if (null != keys) {
                    SimpleSequence sequence = (SimpleSequence) keys;

                    env.getOut().append("<script type='text/javascript'>window.StI18n={");
                    int size = sequence.size();
                    for (int i = 0; i < size; i++) {
                        String key = sequence.get(i).toString();
                        String val = I18nUtils.get(key);
                        if(val.contains("'")){
                            val = StringUtils.replace(val, "'", "\\'");
                        }
                        if(val.endsWith("\\") && !val.endsWith("\\\\")){
                            // 结尾是一个 \ 这种要处理一下，如果更多，就不管了，应该也用不上
                            val = StringUtils.replace(val, "\\", "\\\\");
                        }
                        env.getOut().append("'" + key + "':'" + val + "',");
                    }
                    env.getOut().append("}</script>");
                }

                break;
            case "validate":
                env.getOut().append("<script type='text/javascript' src='/static/plugins/jquery-validation/jquery.validate.min.js'></script>");
                env.getOut().append("<script type='text/javascript' src='/static/plugins/jquery-validation/additional-methods.min.js'></script>");

                // TODO 是否是支持的语言，不是的话，用默认
                env.getOut().append("<script type='text/javascript' src='/static/plugins/jquery-validation/localization/messages_" + service.getLang() + ".min.js'></script>");
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
