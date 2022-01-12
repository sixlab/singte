package cn.sixlab.minesoft.singte.core.common.directive;

import cn.hutool.core.map.MapUtil;
import cn.sixlab.minesoft.singte.core.dao.StKeywordDao;
import cn.sixlab.minesoft.singte.core.models.StKeyword;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class KeywordDirective implements TemplateDirectiveModel {

    @Autowired
    private StKeywordDao keywordDao;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        int num = MapUtil.getInt(params, "num", 5);
        List<StKeyword> keywordList = keywordDao.selectRandom(num);

        env.setVariable("stHotKeywords", ObjectWrapper.DEFAULT_WRAPPER.wrap(keywordList));

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
