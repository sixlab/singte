package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.SteAncient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SteAncientDao extends BaseDao<SteAncient> {

    @Override
    public Class<SteAncient> entityClass() {
        return SteAncient.class;
    }

    public PageResult<SteAncient> selectAncients(String keyword, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();

        if (StringUtils.isNotEmpty(keyword)) {
            criteria = criteria.orOperator(
                    Criteria.where("ancientName").regex(keyword),
                    Criteria.where("ancientType").regex(keyword),
                    Criteria.where("ancientCategory").regex(keyword),
                    Criteria.where("ancientSection").regex(keyword),
                    Criteria.where("ancientAuthor").regex(keyword),
                    Criteria.where("ancientLines").regex(keyword)
            );
        }
        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

    public List<SteAncient> selectByCategory(String category) {
        Criteria criteria = Criteria.where("ancientCategory").is(category);

        Sort sort = Sort.by("ancientName");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }
}