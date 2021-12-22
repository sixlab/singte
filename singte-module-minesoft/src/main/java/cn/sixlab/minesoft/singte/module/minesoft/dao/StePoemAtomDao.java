package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemAtom;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StePoemAtomDao extends BaseDao<StePoemAtom> {

    @Override
    public Class<StePoemAtom> entityClass() {
        return StePoemAtom.class;
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