package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemAtom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StePoemAtomDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StePoemAtom record) {
        mongoTemplate.save(record);
    }

    public StePoemAtom selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StePoemAtom.class);
    }

    public void updateByPrimaryKeySelective(StePoemAtom record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StePoemAtom target = mongoTemplate.findOne(query, StePoemAtom.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StePoemAtom record) {
        mongoTemplate.save(record);
    }

    public List<StePoemAtom> selectByKeywords(String[] keywordList) {
        Criteria criteria = Criteria.where("1").is("1");

        for (String keyword : keywordList) {
            criteria = criteria.and("").regex(".*?" + keyword + ".*?");
        }

        Sort sort = Sort.by("atomOrder");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, StePoemAtom.class);
    }
}