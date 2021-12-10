package cn.sixlab.minesoft.singte.core.common.spider;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.mapper.StArticleMapper;
import cn.sixlab.minesoft.singte.core.mapper.StCategoryMapper;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;

public abstract class SpiderJob {

    @Autowired
    private StArticleMapper articleMapper;

    @Autowired
    private StCategoryMapper categoryMapper;

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
                stCategory.setWeight(1);
                stCategory.setCreateTime(new Date());
                categoryMapper.insert(stCategory);
            }
            article.setCategoryId(stCategory.getId());
        }

        article.setAuthor("spider");
        article.setViewCount(0);
        article.setThumbCount(0);
        article.setPublishStatus(StConst.ST_PUBLISH_DID);
        article.setPublishTime(new Date());
        article.setCreateTime(new Date());

        articleMapper.insert(article);
    }
}
