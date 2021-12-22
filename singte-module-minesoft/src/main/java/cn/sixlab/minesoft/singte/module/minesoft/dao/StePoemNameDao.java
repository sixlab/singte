package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemName;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StePoemNameDao extends BaseDao<StePoemName> {

    @Override
    public Class<StePoemName> entityClass() {
        return StePoemName.class;
    }

    public List<StePoemName> selectPoemNames(Integer pageNum, Integer pageSize) {
        Sort sort = Sort.by("id");

        Query query = new Query().with(sort).skip((pageNum - 1) * pageSize).limit(pageSize);

        return mongoTemplate.find(query, StePoemName.class);
    }
}