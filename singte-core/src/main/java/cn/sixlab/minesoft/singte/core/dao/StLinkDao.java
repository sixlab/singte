package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StLink;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StLinkDao extends BaseDao<StLink> {

    @Override
    public Class<StLink> entityClass() {
        return StLink.class;
    }

    @Override
    public StLink selectExist(StLink record) {
        Criteria criteria = Criteria.where("linkUrl").is(record.getLinkUrl());

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    @Override
    public PageResult<StLink> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("multiDomainGroup").regex(keyword),
                    Criteria.where("linkName").regex(keyword),
                    Criteria.where("linkUrl").regex(keyword),
                    Criteria.where("linkIcon").regex(keyword),
                    Criteria.where("linkType").regex(keyword),
                    Criteria.where("linkGroup").regex(keyword),
                    Criteria.where("linkRemark").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("multiDomainGroup", "weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

    public List<StLink> selectEnableByDomain(String domain) {
        Criteria criteria = Criteria.where("status").is("1")
                .and("multiDomainGroup").is(domain);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public List<StLink> selectGroupByDomain(String domain) {
        Criteria criteria = Criteria.where("status").is("1")
                .and("multiDomainGroup").is(domain);

        // 匹配操作
        MatchOperation matchOperation = Aggregation.match(criteria);

        // 匹配返回的字段 match 有检索条件就要加上去,即使聚合查询没有也要加,否则检索条件不生效
        ProjectionOperation projectionOperation = Aggregation.project(
                "multiDomainGroup",
                "status",

                "linkType",
                "linkGroup",

                "visit",
                "weight");

        // 分组操作
        GroupOperation groupOperation = Aggregation.group("linkType", "linkGroup")
                .addToSet("linkType").as("linkType")
                .addToSet("linkGroup").as("linkGroup")
                .count().as("visit")
                .min("weight").as("weight");


        // GroupOperation groupOperation = Aggregation.group("linkType").addToSet("linkGroup").as("linkGroup").count().as("visit")
        //         .min("weight").as("weight");

        // 排序操作
        SortOperation sortOperation = Aggregation.sort(Sort.by("weight"));

        // 组合条件
        Aggregation aggregation = Aggregation.newAggregation(projectionOperation, matchOperation, groupOperation, sortOperation);

        // 执行操作
        AggregationResults<StLink> aggregate = mongoTemplate.aggregate(aggregation, entityClass(), entityClass());
        return aggregate.getMappedResults();
    }
}