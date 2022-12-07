package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.models.StPage;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StPageDao extends BaseDao<StPage> {

    @Override
    public Class<StPage> entityClass() {
        return StPage.class;
    }

    public StPage selectByAlias(String alias) {
        Criteria criteria = Criteria
                .where("status").is(StConst.YES)
                .and("alias").is(alias);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Query query = new Query(criteria).with(sort).limit(1);

        return mongoTemplate.findOne(query, StPage.class);
    }

    @Override
    public StPage selectExist(StPage record) {
        Criteria criteria = Criteria.where("alias").is(record.getAlias());

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Query query = new Query(criteria).with(sort).limit(1);

        return mongoTemplate.findOne(query, StPage.class);
    }

    public void addView(String id) {
        StPage page = selectById(id);
        page.setViewCount(page.getViewCount() + 1);
        save(page);
    }

    public PageResult<StPage> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("alias").regex(keyword),
                    Criteria.where("title").regex(keyword),
                    Criteria.where("content").regex(keyword),
                    Criteria.where("author").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("publishTime").descending();

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StPage.class, pageNum, pageSize);
    }
}