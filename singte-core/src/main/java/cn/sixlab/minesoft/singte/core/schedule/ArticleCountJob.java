package cn.sixlab.minesoft.singte.core.schedule;

import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.dao.StCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.StKeywordDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import cn.sixlab.minesoft.singte.core.models.StKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ArticleCountJob {

    @Autowired
    private StArticleDao articleDao;

    @Autowired
    private StKeywordDao keywordDao;

    @Autowired
    private StCategoryDao categoryDao;

    /**
     * 定时统计文章信息
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void job() {
        String flag = "update" + new Date().getTime();
        preFlag();

        countCategory(flag);

        countKeyword(flag);

        postFlag(flag);
    }

    private void preFlag() {
        keywordDao.updateFlag("1");
        categoryDao.updateFlag("1");
    }

    private void postFlag(String flag) {
        keywordDao.delWithoutFlag(flag);
        categoryDao.delWithoutFlag(flag);
    }

    private void countCategory(String flag) {
        List<StCategory> categoryList = articleDao.countCategory();
        for (StCategory item : categoryList) {
            StCategory stCategory = categoryDao.selectByCategory(item.getCategory());
            if (null == stCategory) {
                item.setCreateTime(new Date());
                item.setFlag(flag);
                categoryDao.save(item);
                articleDao.updateCategory(item.getCategory(), item.getId());
            } else {
                stCategory.setUpdateTime(new Date());
                stCategory.setArticleCount(item.getArticleCount());
                stCategory.setFlag(flag);
                categoryDao.save(stCategory);
                articleDao.updateCategory(item.getCategory(), stCategory.getId());
            }
        }
    }

    private void countKeyword(String flag) {
        int pageNum = 1;
        Map<String, Integer> keywordCount = new HashMap<>();
        while (pageNum > 0) {
            PageResult<StArticle> pageResult = articleDao.selectByCategory("", pageNum, 10);

            List<StArticle> articleList = pageResult.getList();

            for (StArticle article : articleList) {
                List<String> keywords = article.getKeywords();
                List<String> keywordIds = new ArrayList<>();
                for (String keyword : keywords) {
                    StKeyword stKeyword = keywordDao.selectByKeyword(keyword);
                    if (stKeyword == null) {
                        stKeyword = new StKeyword();
                        stKeyword.setKeyword(keyword);
                        stKeyword.setArticleCount(0);
                        stKeyword.setCreateTime(new Date());
                        keywordDao.save(stKeyword);
                    }
                    String keywordId = stKeyword.getId();
                    keywordIds.add(keywordId);
                    int count = keywordCount.getOrDefault(keywordId, 0) + 1;
                    keywordCount.put(keywordId, count);
                }
                article.setKeywordIds(keywordIds);
                articleDao.save(article);
            }

            if (pageResult.isHasNext()) {
                pageNum++;
            } else {
                pageNum = 0;
            }
        }

        keywordCount.forEach((key, val) -> {
            StKeyword stKeyword = keywordDao.selectById(key);
            stKeyword.setArticleCount(val);
            stKeyword.setUpdateTime(new Date());
            stKeyword.setFlag(flag);
            keywordDao.save(stKeyword);
        });
    }

}
