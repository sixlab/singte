package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StSpider;
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

    public StSpider selectByName(String spiderName) {
        Query query = new Query(Criteria.where("spiderName").is(spiderName)).with(Sort.by("id"));
        return mongoTemplate.findOne(query, entityClass());
    }

    /**
     * 查询已经启用的爬虫任务
     * 
     * @param status
     * @return
     */
    public List<StSpider> selectByStatus(String status) {
        Query query = new Query(Criteria.where("status").is(status)).with(Sort.by("id"));
        return mongoTemplate.find(query, entityClass());
    }

    public PageResult<StSpider> querySpider(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
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

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}