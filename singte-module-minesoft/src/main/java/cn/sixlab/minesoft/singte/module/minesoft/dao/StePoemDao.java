package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StePoemDao extends BaseDao<StePoem> {

    @Override
    public Class<StePoem> entityClass() {
        return StePoem.class;
    }

    public PageResult<StePoem> selectPoems(String keyword, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();

        if (StringUtils.isNotEmpty(keyword)) {
            criteria = criteria.orOperator(
                    Criteria.where("poemName").regex(keyword),
                    Criteria.where("poemType").regex(keyword),
                    Criteria.where("poemCategory").regex(keyword),
                    Criteria.where("poemSection").regex(keyword),
                    Criteria.where("poemAuthor").regex(keyword),
                    Criteria.where("poemLines").regex(keyword)
            );
        }
        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StePoem.class, pageNum, pageSize);
    }

}