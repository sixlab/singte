package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StSpiderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StSpider record) {
        mongoTemplate.save(record);
    }

    public StSpider selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StSpider.class);
    }

    public void updateByPrimaryKeySelective(StSpider record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StSpider target = mongoTemplate.findOne(query, StSpider.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StSpider record) {
        mongoTemplate.save(record);
    }

    public List<StSpider> selectByStatus(String status) {
        Query query = new Query(Criteria.where("status").is(status)).with(Sort.by("id"));
        return mongoTemplate.find(query, StSpider.class);
    }
}