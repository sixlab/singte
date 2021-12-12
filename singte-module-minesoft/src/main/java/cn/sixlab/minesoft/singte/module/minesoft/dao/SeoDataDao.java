package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeoDataDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(SeoData record) {
        mongoTemplate.save(record);
    }

    public SeoData selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, SeoData.class);
    }

    public void updateByPrimaryKeySelective(SeoData record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        SeoData target = mongoTemplate.findOne(query, SeoData.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(SeoData record) {
        mongoTemplate.save(record);
    }

    public List<SeoData> selectByDate(String date){
        Criteria criteria = Criteria.where("date").is(date);

        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoData.class);
    }

    public List<SeoData> selectBeforeDate(String date){
        Criteria criteria = Criteria.where("date").gt(date);

        Sort sort = Sort.by("date");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoData.class);
    }

    public List<SeoData> selectUserCategory(String uid, String category, String date) {
        Criteria criteria = Criteria.where("uid").gt(uid)
                .and("category").is(category)
                .and("date").gt(date);

        Sort sort = Sort.by("date");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoData.class);
    }

    public SeoData selectUserDateCategory(String uid, String date, String category){
        Criteria criteria = Criteria.where("uid").gt(uid)
                .and("category").is(category)
                .and("date").is(date);

        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, SeoData.class);
    }
}