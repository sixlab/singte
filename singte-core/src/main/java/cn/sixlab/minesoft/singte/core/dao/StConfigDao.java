package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StConfigDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StConfig record) {
        mongoTemplate.save(record);
    }

    public StConfig selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StConfig.class);
    }

    public void updateByPrimaryKeySelective(StConfig record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StConfig target = mongoTemplate.findOne(query, StConfig.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StConfig record) {
        mongoTemplate.save(record);
    }

    public void init() {

    }

    public void runSQL(String sql) {

    }

    public StConfig selectByKey(String configKey) {
        Query query = new Query(Criteria.where("configKey").is(configKey));
        return mongoTemplate.findOne(query, StConfig.class);
    }
}