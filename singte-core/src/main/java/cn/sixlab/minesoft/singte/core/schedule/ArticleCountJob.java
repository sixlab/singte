package cn.sixlab.minesoft.singte.core.schedule;

import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArticleCountJob {

    @Autowired
    private ArticleService articleService;

    /**
     * 定时统计文章信息
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void job() {
        articleService.countCategory();

        articleService.countKeyword();
    }

}
