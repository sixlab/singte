package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StSpiderResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StSpiderResultDao extends BaseDao<StSpiderResult> {

    @Override
    public Class<StSpiderResult> entityClass() {
        return StSpiderResult.class;
    }

    @Override
    public StSpiderResult selectExist(StSpiderResult record) {
        return null;
    }

    public PageResult<StSpiderResult> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("spiderCode").regex(keyword),
                    Criteria.where("spiderType").regex(keyword),
                    Criteria.where("spiderName").regex(keyword),
                    Criteria.where("spiderLink").regex(keyword),
                    Criteria.where("title").regex(keyword),
                    Criteria.where("content").regex(keyword),
                    Criteria.where("summary").regex(keyword),
                    Criteria.where("category").regex(keyword),
                    Criteria.where("keyword").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

}