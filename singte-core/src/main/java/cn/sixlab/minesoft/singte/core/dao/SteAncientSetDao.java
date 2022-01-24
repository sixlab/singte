package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.SteAncientSet;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SteAncientSetDao extends BaseDao<SteAncientSet> {

    @Override
    public Class<SteAncientSet> entityClass() {
        return SteAncientSet.class;
    }

    public List<SteAncientSet> list(){
        Criteria criteria = new Criteria();
        Sort sort = Sort.by("weight", "id");
        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public SteAncientSet selectByName(String ancientSet) {
        Criteria criteria = Criteria.where("ancientSet").is(ancientSet);

        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<SteAncientSet> queryAncientSet(String keyword, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();

        if (StrUtil.isNotEmpty(keyword)) {
            criteria = criteria.orOperator(
                    Criteria.where("ancientSet").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );
        }
        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}