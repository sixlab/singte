package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StSpiderDao extends BaseDao<StSpider> {

    @Override
    public Class<StSpider> entityClass() {
        return StSpider.class;
    }

    /**
     * 查询已经启用的爬虫任务
     * 
     * @param status
     * @return
     */
    public List<StSpider> selectByStatus(String status) {
        Query query = new Query(Criteria.where("status").is(status)).with(Sort.by("id"));
        return mongoTemplate.find(query, StSpider.class);
    }

    public PageResult<StSpider> selectSpiders(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(status)) {
            criteria = criteria.and("spiderStatus").is(status);
        }

        if (StringUtils.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("spiderName").regex(keyword),
                    Criteria.where("startUrl").regex(keyword),
                    Criteria.where("pagerRule").regex(keyword),
                    Criteria.where("linkRule").regex(keyword),
                    Criteria.where("titleRule").regex(keyword),
                    Criteria.where("contentRule").regex(keyword),
                    Criteria.where("summaryRule").regex(keyword),
                    Criteria.where("categoryRule").regex(keyword),
                    Criteria.where("keywordRule").regex(keyword),
                    Criteria.where("urlParam").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("createTime").descending();

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StSpider.class, pageNum, pageSize);
    }
}