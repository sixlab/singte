package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StLinkDomain;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StLinkDomainDao extends BaseDao<StLinkDomain> {

    @Override
    public Class<StLinkDomain> entityClass() {
        return StLinkDomain.class;
    }

    @Override
    public StLinkDomain selectExist(StLinkDomain record) {
        Criteria criteria = Criteria.where("domain").is(record.getDomain());

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    @Override
    public PageResult<StLinkDomain> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("domainName").regex(keyword),
                    Criteria.where("domain").regex(keyword),
                    Criteria.where("multiDomainGroup").regex(keyword),
                    Criteria.where("domainTitle").regex(keyword),
                    Criteria.where("domainKeyword").regex(keyword),
                    Criteria.where("domainDescription").regex(keyword),
                    Criteria.where("domainRemark").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

    public StLinkDomain selectEnableByDomain(String domain) {
        Criteria criteria = Criteria.where("status").is("1")
                .and("domain").is(domain);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

}