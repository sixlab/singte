package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StePoemNameDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StePoemName record) {
        mongoTemplate.save(record);
    }

    public StePoemName selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StePoemName.class);
    }

    public void updateByPrimaryKeySelective(StePoemName record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StePoemName target = mongoTemplate.findOne(query, StePoemName.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StePoemName record) {
        mongoTemplate.save(record);
    }

    public List<StePoemName> selectPoemNames(Integer pageNum, Integer pageSize) {
        Sort sort = Sort.by("id");

        Query query = new Query().with(sort).skip((pageNum - 1) * pageSize).limit(pageSize);

        return mongoTemplate.find(query, StePoemName.class);
    }
}