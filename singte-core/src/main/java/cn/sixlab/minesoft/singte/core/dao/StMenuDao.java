package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StMenuDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StMenu record) {
        mongoTemplate.save(record);
    }

    public StMenu selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StMenu.class);
    }

    public void updateByPrimaryKeySelective(StMenu record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StMenu target = mongoTemplate.findOne(query, StMenu.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StMenu record) {
        mongoTemplate.save(record);
    }

    public List<StMenu> selectGroupMenus(String menuGroup) {
        Query query = new Query(Criteria.where("menuGroup").is(menuGroup)).with(Sort.by("weight"));
        return mongoTemplate.find(query, StMenu.class);
    }
}