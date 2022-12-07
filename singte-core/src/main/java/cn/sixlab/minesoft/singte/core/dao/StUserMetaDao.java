package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.models.StUserMeta;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StUserMetaDao extends BaseDao<StUserMeta> {

    @Override
    public Class<StUserMeta> entityClass() {
        return StUserMeta.class;
    }

    @Override
    public StUserMeta selectExist(StUserMeta record) {
        Criteria criteria = Criteria.where("username").is(record.getUsername()).and("metaKey").is(record.getMetaKey());

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<StUserMeta> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("username").regex(keyword),
                    Criteria.where("metaKey").regex(keyword),
                    Criteria.where("metaVal").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

    public StUserMeta selectUserMeta(String username, String metaKey) {
        Criteria criteria = Criteria.where("status").is(StConst.YES)
                .and("username").is(username)
                .and("metaKey").is(metaKey);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public List<StUserMeta> selectUserMetaList(String username, String metaKey) {
        Criteria criteria = Criteria.where("status").is(StConst.YES);

        if (StrUtil.isNotEmpty(username)) {
            criteria = criteria.and("username").is(username);
        }

        if (StrUtil.isNotEmpty(metaKey)) {
            criteria = criteria.and("metaKey").is(metaKey);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public StUserMeta selectByMeta(String metaKey, String metaVal) {
        Criteria criteria = Criteria.where("status").is(StConst.YES)
                .and("metaKey").is(metaKey)
                .and("metaVal").is(metaVal);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }
}