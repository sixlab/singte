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

        Sort sort = Sort.by("weight");

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

    public PageResult<SteAncientCategory> selectAncientCategory(String keyword, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();

        if (StrUtil.isNotEmpty(keyword)) {
            criteria = criteria.orOperator(
                    Criteria.where("ancientSet").regex(keyword),
                    Criteria.where("ancientCategory").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );
        }
        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}