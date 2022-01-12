package cn.sixlab.minesoft.singte.core.common.spider;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.dao.StCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.StKeywordDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import cn.sixlab.minesoft.singte.core.models.StKeyword;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class SpiderJob {

    @Autowired
    private StArticleDao articleMapper;

    @Autowired
    private StKeywordDao keywordDao;

    @Autowired
    private StCategoryDao categoryMapper;

    public abstract void craw(StSpider spider);

    public boolean isCrawed(String url){
        StArticle article = articleMapper.selectBySourceUrl(url);
        return null == article;
    }

    public Document fetchDom(String url, Integer waitTimes) {
        try {
            Document document = Jsoup.connect(url).get();
            if (waitTimes > 0) {
                Thread.sleep(waitTimes);
            }
            return document;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String jxNodeVal(JXNode jxNode) {
        if (jxNode != null) {
            if (jxNode.isElement()) {
                return jxNode.asElement().text();
            }else{
                return String.valueOf(jxNode.value());
            }
        }

        return null;
    }

    public void saveContent(StSpider spider, StArticle article) {
        String category = article.getCategory();

        if(StringUtils.isNotEmpty(category)){
            StCategory stCategory = categoryMapper.selectByCategory(category);
            if (stCategory == null) {
                stCategory = new StCategory();
                stCategory.setCategory(category);
                stCategory.setArticleCount(1);
                stCategory.setWeight(1);
                stCategory.setCreateTime(new Date());
            }else{
                stCategory.setUpdateTime(new Date());
                stCategory.setArticleCount(stCategory.getArticleCount() + 1);
            }
            categoryMapper.save(stCategory);
        }

        List<String> keywords = article.getKeywords();
        for (String keyword : keywords) {
            StKeyword stKeyword = keywordDao.selectByKeyword(keyword);
            if (stKeyword == null) {
                stKeyword = new StKeyword();
                stKeyword.setKeyword(keyword);
                stKeyword.setArticleCount(1);
                stKeyword.setCreateTime(new Date());
            }else{
                stKeyword.setUpdateTime(new Date());
                stKeyword.setArticleCount(stKeyword.getArticleCount() + 1);
            }
            keywordDao.save(stKeyword);
        }

        article.setAuthor("spider");
        article.setViewCount(0);
        article.setThumbCount(0);
        article.setStatus(StConst.ST_PUBLISH_DID);
        article.setPublishTime(new Date());
        article.setCreateTime(new Date());

        articleMapper.save(article);
    }
}
