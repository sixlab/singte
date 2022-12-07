package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StSpiderPush;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StSpiderPushDao extends BaseDao<StSpiderPush> {

    @Override
    public Class<StSpiderPush> entityClass() {
        return StSpiderPush.class;
    }

    @Override
    public StSpiderPush selectExist(StSpiderPush record) {
        // TODO
        Criteria criteria = Criteria.where("taskCode").is(record.getId());

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<StSpiderPush> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            // TODO
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("spiderCode").regex(keyword),
                    Criteria.where("spiderBean").regex(keyword),
                    Criteria.where("spiderName").regex(keyword),
                    Criteria.where("spiderLink").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

}