package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StConfigDao extends BaseDao<StConfig> {

    @Override
    public Class<StConfig> entityClass() {
        return StConfig.class;
    }

    public StConfig selectByKey(String configKey) {
        Query query = new Query(Criteria.where("configKey").is(configKey));
        return mongoTemplate.findOne(query, StConfig.class);
    }
}