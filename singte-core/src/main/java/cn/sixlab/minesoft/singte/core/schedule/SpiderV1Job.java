package cn.sixlab.minesoft.singte.core.schedule;

import cn.sixlab.minesoft.singte.core.common.spider.SpiderJob;
import cn.sixlab.minesoft.singte.core.common.utils.CtxHolder;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.mapper.StArticleMapper;
import cn.sixlab.minesoft.singte.core.mapper.StCategoryMapper;
import cn.sixlab.minesoft.singte.core.mapper.StSpiderMapper;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpiderV1Job {

    @Autowired
    private StSpiderMapper spiderMapper;

    @Scheduled(cron = "0 0 22 * * ?")
    public void job() {
        List<StSpider> spiderList = spiderMapper.selectByStatus(StConst.YES);

        spiderList.forEach(spider -> {
            CtxHolder.getBean(spider.getSpiderType(), SpiderJob.class).craw(spider);
        });
    }

}
