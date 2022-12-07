package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SteAncientCategoryDao extends BaseDao<SteAncientCategory> {

    @Override
    public Class<SteAncientCategory> entityClass() {
        return SteAncientCategory.class;
    }

    public List<SteAncientCategory> listSetCategory(String ancientSet){
        Criteria criteria = Criteria.where("ancientSet").is(ancientSet);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public SteAncientCategory selectBySetAndName(String ancientSet, String ancientCategory) {
        Criteria criteria = Criteria.where("ancientSet").is(ancientSet)
                .and("ancientCategory").is(ancientCategory);

        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    @Override
    public SteAncientCategory selectExist(SteAncientCategory record) {
        Criteria criteria = Criteria.where("ancientSet").is(record.getAncientSet())
                .and("ancientCategory").is(record.getAncientCategory());

        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<SteAncientCategory> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("ancientSet").regex(keyword),
                    Criteria.where("ancientCategory").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}