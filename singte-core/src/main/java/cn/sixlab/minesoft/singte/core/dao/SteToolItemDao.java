package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.SteToolItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SteToolItemDao extends BaseDao<SteToolItem> {

    @Override
    public Class<SteToolItem> entityClass() {
        return SteToolItem.class;
    }

    public List<SteToolItem> listByCategory(String category){
        Criteria criteria = Criteria.where("category").is(category);

        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public PageResult<SteToolItem> selectTools(String keyword, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();

        if (StringUtils.isNotEmpty(keyword)) {
            criteria = criteria.orOperator(
                    Criteria.where("toolName").regex(keyword),
                    Criteria.where("category").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );
        }
        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}