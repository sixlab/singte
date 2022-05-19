package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StDomain;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StDomainDao extends BaseDao<StDomain> {

    @Override
    public Class<StDomain> entityClass() {
        return StDomain.class;
    }

    @Override
    public StDomain selectExist(StDomain record) {
        Criteria criteria = Criteria.where("domainUrl").is(record.getDomainUrl());

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    @Override
    public PageResult<StDomain> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("domainName").regex(keyword),
                    Criteria.where("domainUrl").regex(keyword),
                    Criteria.where("tplPath").regex(keyword),
                    Criteria.where("domainBean").regex(keyword),
                    Criteria.where("domainRemark").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

    public List<StDomain> selectStatus(String status) {
        Criteria criteria = Criteria.where("status").is(status);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public List<StDomain> selectByBean(String domainBean) {
        Criteria criteria = Criteria.where("status").is("1").and("domainBean").is(domainBean);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public StDomain selectActiveDomain(String domain) {
        Criteria criteria = Criteria.where("domainUrl").is(domain).and("status").is("1");

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public StDomain selectHighWeight() {
        Criteria criteria = Criteria.where("status").is("1");

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }
}