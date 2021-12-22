package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StePoemDao extends BaseDao<StePoem> {

    @Override
    public Class<StePoem> entityClass() {
        return StePoem.class;
    }

    public List<StePoem> selectPoems() {
        Sort sort = Sort.by("id");

        Query query = new Query().with(sort);

        return mongoTemplate.find(query, StePoem.class);
    }
}