package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StCategoryDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StCategory record) {
        mongoTemplate.save(record);
    }


    public StCategory selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StCategory.class);
    }

    public void updateByPrimaryKeySelective(StCategory record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StCategory target = mongoTemplate.findOne(query, StCategory.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StCategory record) {
        mongoTemplate.save(record);
    }

    public StCategory selectByCategory(String category) {
        Query query = new Query(Criteria.where("category").is(category)).with(Sort.by("id"));
        return mongoTemplate.findOne(query, StCategory.class);
    }
}