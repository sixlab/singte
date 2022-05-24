package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StLinkType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StLinkTypeDao extends BaseDao<StLinkType> {

    @Override
    public Class<StLinkType> entityClass() {
        return StLinkType.class;
    }

    @Override
    public StLinkType selectExist(StLinkType record) {
        Criteria criteria = Criteria.where("multiDomainGroup").is(record.getMultiDomainGroup())
                .and("linkType").is(record.getLinkType());

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    @Override
    public PageResult<StLinkType> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("multiDomainGroup").regex(keyword),
                    Criteria.where("linkType").regex(keyword),
                    Criteria.where("linkRemark").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("multiDomainGroup", "weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

    public List<StLinkType> selectEnableByDomain(String domainGroup) {
        Criteria criteria = Criteria.where("status").is("1")
                .and("multiDomainGroup").is(domainGroup);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

}