package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StLang;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StLangDao extends BaseDao<StLang> {

    @Override
    public Class<StLang> entityClass() {
        return StLang.class;
    }

    public StLang selectByLang(String langCode) {
        Query query = new Query(Criteria.where("langCode").is(langCode));
        return mongoTemplate.findOne(query, entityClass());
    }

    public List<StLang> selectLang(String status) {
        Query query = new Query(Criteria.where("status").is(status));
        return mongoTemplate.find(query, entityClass());
    }

    public PageResult<StLang> selectLangList(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("langCode").regex(keyword),
                    Criteria.where("langText").regex(keyword),
                    Criteria.where("langIcon").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("createTime").descending();

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}