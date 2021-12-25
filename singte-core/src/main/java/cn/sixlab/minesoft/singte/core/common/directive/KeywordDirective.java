package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.dao.StMenuDao;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class KeywordDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        List<String> keywordList = null;

        env.setVariable("stHotKeywords", ObjectWrapper.DEFAULT_WRAPPER.wrap(keywordList));

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
