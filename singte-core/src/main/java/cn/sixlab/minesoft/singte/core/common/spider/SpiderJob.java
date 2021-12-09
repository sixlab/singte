package cn.sixlab.minesoft.singte.core.common.spider;

import cn.sixlab.minesoft.singte.core.mapper.StArticleMapper;
import cn.sixlab.minesoft.singte.core.mapper.StCategoryMapper;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class SpiderJob {

    @Autowired
    private StArticleMapper articleMapper;

    @Autowired
    private StCategoryMapper categoryMapper;

    public abstract void craw(StSpider spider);

    public Document fetchDom(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String jxNodeVal(JXNode jxNode) {
        if (jxNode != null) {
            Object value = jxNode.value();
            if (value != null) {
                return String.valueOf(value);
            }
        }

        return null;
    }

    public void saveContent(StSpider spider, String url, StArticle article) {

    }
}
