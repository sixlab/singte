package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ArticlesDirective implements TemplateDirectiveModel {

    @Autowired
    private StArticleDao articleDao;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String type = MapUtils.getString(params, "type", "random");
        int size = Integer.parseInt(MapUtils.getString(params, "size", "10"));

        List<StArticle> articleList = null;
        switch (type) {
            case "hot":
                articleList = articleDao.selectHot(size);
                break;
            case "last":
                articleList = articleDao.selectLast(size);
                break;
            case "random":
                articleList = articleDao.selectRandom(size);
                break;
            case "view":
                articleList = articleDao.selectView(size);
                break;
        }

        env.setVariable("stArticleList", ObjectWrapper.DEFAULT_WRAPPER.wrap(articleList));

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
