package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.models.StData;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StDataDao extends BaseDao<StData> {

    @Override
    public Class<StData> entityClass() {
        return StData.class;
    }

    public List<StData> selectByGroup(String dataGroup){
        Query query = new Query(Criteria.where("dataGroup").is(dataGroup));
        return mongoTemplate.find(query, StData.class);
    }

    public StData selectByGroupKey(String dataGroup, String dataKey){
        Query query = new Query(Criteria.where("dataGroup").is(dataGroup).and("dataKey").is(dataKey)).with(Sort.by("id"));
        return mongoTemplate.findOne(query, StData.class);
    }
}