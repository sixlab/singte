package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.models.StUser;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class UserDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        StUser stUser = WebUtils.getLoginUser();
        if (null != stUser) {
            env.setVariable("stUser", ObjectWrapper.DEFAULT_WRAPPER.wrap(stUser));
        }

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
