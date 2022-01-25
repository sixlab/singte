package cn.sixlab.minesoft.singte.core.common.spider;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.common.vo.StSpiderParam;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("PageLinkSpider")
public class PageLinkSpider extends SpiderJob {
    @Override
    public void craw(StSpider spider) {
        String startUrl = spider.getStartUrl();
        String spiderParam = spider.getSpiderParam();
        StSpiderParam param = JsonUtils.toBean(spiderParam, StSpiderParam.class);
        for (int i = param.getBegin(); i < param.getEnd(); i++) {
            String link = startUrl.replace("{num}", i + "");
            crawPage(spider, link);
        }
    }

    private void crawPage(StSpider spider, String pageUrl) {
        Document dom = fetchDom(pageUrl, spider.getWaitTimes());

        JXDocument jxd = new JXDocument(dom.getAllElements());
        List<JXNode> rs = jxd.selN(spider.getLinkRule());

        for (JXNode node : rs) {
            String link = String.valueOf(node.value());
            if (isCrawed(link)) {
                crawContent(link, spider);
            }
        }
    }

    private void crawContent(String url, StSpider spider) {
        Document dom = fetchDom(url, spider.getWaitTimes());

        JXDocument jxd = new JXDocument(dom.getAllElements());
        JXNode titleNode = jxd.selNOne(spider.getTitleRule());
        JXNode summaryNode = jxd.selNOne(spider.getSummaryRule());
        JXNode contentNode = jxd.selNOne(spider.getContentRule());
        JXNode categoryNode = jxd.selNOne(spider.getCategoryRule());
        List<JXNode> keywordNodeList = jxd.selN(spider.getKeywordRule());

        StArticle article = new StArticle();

        String html = null;
        if (contentNode != null) {
            html = contentNode.asElement().outerHtml();
        }
        if (StrUtil.isNotEmpty(html)) {
            article.setContent(html);
        }else{
            return;
        }

        article.setTitle(jxNodeVal(titleNode));
        article.setSummary(jxNodeVal(summaryNode));
        article.setCategory(jxNodeVal(categoryNode));

        List<String> keywordList = new ArrayList<>();
        if(CollUtil.isNotEmpty(keywordNodeList)){
            keywordNodeList.forEach(jxNode -> {
                String val = jxNodeVal(jxNode);
                if (StrUtil.isNotEmpty(val)) {
                    keywordList.add(val);
                }
            });
        }
        article.setKeywords(keywordList);
        article.setSourceUrl(url);

        saveContent(spider, article);
    }
}
