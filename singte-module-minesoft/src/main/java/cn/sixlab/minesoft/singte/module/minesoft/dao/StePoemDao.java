package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StePoemDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StePoem record) {
        mongoTemplate.save(record);
    }

    public StePoem selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StePoem.class);
    }

    public void updateByPrimaryKeySelective(StePoem record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StePoem target = mongoTemplate.findOne(query, StePoem.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StePoem record) {
        mongoTemplate.save(record);
    }

    public List<StePoem> selectPoems(boolean withBLOBs) {
        Sort sort = Sort.by("id");

        Query query = new Query().with(sort);

        return mongoTemplate.find(query, StePoem.class);
    }
}