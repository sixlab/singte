package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.SteToolCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SteToolCategoryDao extends BaseDao<SteToolCategory> {

    @Override
    public Class<SteToolCategory> entityClass() {
        return SteToolCategory.class;
    }

    public List<SteToolCategory> list(){
        Criteria criteria = new Criteria();
        Sort sort = Sort.by("weight", "id");
        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public SteToolCategory selectByName(String category) {
        Criteria criteria = Criteria.where("category").is(category);

        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    @Override
    public SteToolCategory selectExist(SteToolCategory record) {
        Criteria criteria = Criteria.where("category").is(record.getCategory());

        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<SteToolCategory> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("category").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}