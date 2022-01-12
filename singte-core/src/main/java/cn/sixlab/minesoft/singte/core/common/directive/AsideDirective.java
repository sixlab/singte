package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StWidgetDao;
import cn.sixlab.minesoft.singte.core.models.StWidget;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class AsideDirective implements TemplateDirectiveModel {

    @Autowired
    private StWidgetDao widgetDao;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        List<StWidget> widgetList = widgetDao.selectByStatus(StConst.YES);

        env.setVariable("stWidgetList", ObjectWrapper.DEFAULT_WRAPPER.wrap(widgetList));

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
