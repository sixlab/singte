package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeoItemDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(SeoItem record) {
        mongoTemplate.save(record);
    }

    public SeoItem selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, SeoItem.class);
    }

    public void updateByPrimaryKeySelective(SeoItem record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        SeoItem target = mongoTemplate.findOne(query, SeoItem.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(SeoItem record) {
        mongoTemplate.save(record);
    }

    public List<SeoItem> selectItems(String dataType){
        Criteria criteria = Criteria.where("dataType").is(dataType);

        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoItem.class);
    }

    public List<SeoItem> selectByCategory(String category){
        Criteria criteria = Criteria.where("category").is(category);

        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoItem.class);
    }

    public SeoItem selectUser(String dataType, String uid){
        Criteria criteria = Criteria.where("uid").is(uid)
                .and("dataType").is(dataType);

        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, SeoItem.class);
    }

}