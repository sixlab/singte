package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StLangDao;
import cn.sixlab.minesoft.singte.core.models.StLang;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class LangDirective implements TemplateDirectiveModel {

    @Autowired
    private StLangDao langDao;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        List<StLang> supportLangList = langDao.selectLang(StConst.YES);

        String lang = LocaleContextHolder.getLocale().toString();
        for (StLang stLang : supportLangList) {
            if(stLang.getLangCode().equals(lang)){
                env.setVariable("stCurrentLangIcon", ObjectWrapper.DEFAULT_WRAPPER.wrap(stLang.getLangIcon()));
                stLang.setStatus("2");
                break;
            }
        }

        env.setVariable("stLangList", ObjectWrapper.DEFAULT_WRAPPER.wrap(supportLangList));
        if (body != null) {
            body.render(env.getOut());
        }
    }
}
