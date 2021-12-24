package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StArticleDao extends BaseDao<StArticle> {

    @Override
    public Class<StArticle> entityClass() {
        return StArticle.class;
    }

    public StArticle selectById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StArticle.class);
    }

    public void updateSelective(StArticle record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StArticle target = mongoTemplate.findOne(query, StArticle.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateById(StArticle record) {
        mongoTemplate.save(record);
    }

    public List<StArticle> selectHot(int size) {
        Query query = new Query(Criteria.where("publishStatus").is(StConst.YES))
                .with(Sort.by(Sort.Direction.DESC, "viewCount"))
                .limit(size);
        return mongoTemplate.find(query, StArticle.class);
    }

    public List<StArticle> selectLast(int size) {
        Query query = new Query(Criteria.where("publishStatus").is(StConst.YES))
                .with(Sort.by(Sort.Direction.DESC, "publishTime"))
                .limit(size);
        return mongoTemplate.find(query, StArticle.class);
    }

    public List<StArticle> selectRandom(int size) {
        Criteria criteria = Criteria.where("publishStatus").is(StConst.YES);
        MatchOperation matchOperation = Aggregation.match(criteria);
        SampleOperation sizeOperation = Aggregation.sample(size);
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, sizeOperation);
        AggregationResults<StArticle> output = mongoTemplate.aggregate(aggregation, StArticle.class, StArticle.class);

        return output.getMappedResults();
    }

    public PageResult<StArticle> selectByCategory(String category, int pageNum, int pageSize) {
        Criteria criteria = Criteria.where("publishStatus").is(StConst.YES);
        Sort sort = Sort.by(Sort.Direction.DESC, "publishTime");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StArticle.class, pageNum, pageSize);
    }

    public StArticle selectByAlias(String alias) {
        Criteria criteria = Criteria
                .where("publishStatus").is(StConst.YES)
                .and("alias").is(alias);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Query query = new Query(criteria).with(sort).limit(1);

        return mongoTemplate.findOne(query, StArticle.class);
    }

    public void addView(String id) {
        StArticle article = selectById(id);
        article.setViewCount(article.getViewCount() + 1);
        updateById(article);
    }

    public PageResult<StArticle> selectByDate(Date begin, Date end, int pageNum, int pageSize) {
        Criteria criteria = Criteria.where("publishStatus").is(StConst.YES)
                .and("publishTime").gte(begin)
                .and("publishTime").lt(end);
        Sort sort = Sort.by(Sort.Direction.DESC, "publishTime");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StArticle.class, pageNum, pageSize);
    }

    public StArticle selectBySourceUrl(String sourceUrl) {
        Criteria criteria = Criteria.where("sourceUrl").is(sourceUrl);

        Query query = new Query(criteria).limit(1);

        return mongoTemplate.findOne(query, StArticle.class);
    }
}