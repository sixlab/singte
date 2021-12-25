package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.models.StKeyword;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StKeywordDao extends BaseDao<StKeyword> {

    @Override
    public Class<StKeyword> entityClass() {
        return StKeyword.class;
    }

    /**
     * 根据关键词查询关键词
     *
     * @param keyword 关键词
     * @return 关键词信息
     */
    public StKeyword selectByKeyword(String keyword) {
        Query query = new Query(Criteria.where("keyword").is(keyword)).with(Sort.by("id"));
        return mongoTemplate.findOne(query, StKeyword.class);
    }

    /**
     * 随机查询关键词
     *
     * @param size 数量
     * @return 关键词列表
     */
    public List<StKeyword> selectRandom(int size) {
        SampleOperation sizeOperation = Aggregation.sample(size);
        Aggregation aggregation = Aggregation.newAggregation(sizeOperation);
        AggregationResults<StKeyword> output = mongoTemplate.aggregate(aggregation, StKeyword.class, StKeyword.class);

        return output.getMappedResults();
    }
}