package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
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
    private ArticleService articleService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String type = MapUtils.getString(params, "type", "random");
        int size = Integer.parseInt(MapUtils.getString(params, "size", "10"));
        int num = Integer.parseInt(MapUtils.getString(params, "num", "1"));
        String word = MapUtils.getString(params, "word", "");

        List<StArticle> articleList = null;
        PageResult<StArticle> pageInfo = null;
        switch (type) {
            case "hot":
                articleList = articleService.topHot(size);
                break;
            case "last":
                articleList = articleService.topLast(size);
                break;
            case "random":
                articleList = articleService.random(size);
                break;
            case "date":
                String date = MapUtils.getString(params, "date");
                pageInfo = articleService.selectByDate(date, num, size);
                break;

            case "category":
                pageInfo = articleService.selectCategory(word, num, size);
                break;
            case "keyword":
                pageInfo = articleService.selectKeyword(word, num, size);
                break;
            case "search":
                pageInfo = articleService.selectSearch(word, num, size);
                break;
            case "index":
                pageInfo = articleService.selectCategory("", 1, size);
                break;
            case "list":
                pageInfo = articleService.selectCategory("", num, size);
                break;
        }

        if(null!=pageInfo){
            env.setVariable("tArticlePageInfo", ObjectWrapper.DEFAULT_WRAPPER.wrap(pageInfo));
        }
        env.setVariable("tArticleList", ObjectWrapper.DEFAULT_WRAPPER.wrap(articleList));

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
