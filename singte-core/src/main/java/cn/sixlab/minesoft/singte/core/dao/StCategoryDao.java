package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StCategoryDao extends BaseDao<StCategory> {

    @Override
    public Class<StCategory> entityClass() {
        return StCategory.class;
    }

    public StCategory selectByCategory(String category) {
        Query query = new Query(Criteria.where("category").is(category)).with(Sort.by("id"));
        return mongoTemplate.findOne(query, StCategory.class);
    }
}