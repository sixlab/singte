package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.models.StUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StUserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StUser record) {
        mongoTemplate.save(record);
    }

    public StUser selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StUser.class);
    }

    public void updateByPrimaryKeySelective(StUser record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StUser target = mongoTemplate.findOne(query, StUser.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StUser record) {
        mongoTemplate.save(record);
    }

    public StUser selectByUsername(String username) {
        Query query = new Query(
                Criteria.where("username").is(username)
                        .and("status").is(StConst.YES)

        );
        return mongoTemplate.findOne(query, StUser.class);
    }
}