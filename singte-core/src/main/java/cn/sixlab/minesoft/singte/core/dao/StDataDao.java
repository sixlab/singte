package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.core.models.StData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StDataDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StData record){
        mongoTemplate.save(record);
    }



    public StData selectByPrimaryKey(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StData.class);
    }

    public void updateByPrimaryKeySelective(StData record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StData target = mongoTemplate.findOne(query, StData.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StData record){
        mongoTemplate.save(record);
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