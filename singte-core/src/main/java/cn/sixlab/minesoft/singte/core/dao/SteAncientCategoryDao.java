package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import org.apache.commons.lang3.StringUtils;
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

    public List<SteAncientCategory> listCategory(){
        Criteria criteria = new Criteria();
        Sort sort = Sort.by("categoryOrder");
        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public PageResult<SteAncientCategory> selectAncients(String keyword, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();

        if (StringUtils.isNotEmpty(keyword)) {
            criteria = criteria.orOperator(
                    Criteria.where("ancientCategory").regex(keyword),
                    Criteria.where("categoryIntro").regex(keyword)
            );
        }
        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

}