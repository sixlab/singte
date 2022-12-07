package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StConfigDao extends BaseDao<StConfig> {

    @Override
    public Class<StConfig> entityClass() {
        return StConfig.class;
    }

    /**
     * 查询配置
     *
     * @param configKey 配置 key
     * @return 配置信息
     */
    public StConfig selectByKey(String configKey) {
        Query query = new Query(Criteria.where("configKey").is(configKey));
        return mongoTemplate.findOne(query, entityClass());
    }

    @Override
    public StConfig selectExist(StConfig record) {
        Query query = new Query(Criteria.where("configKey").is(record.getConfigKey()));
        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<StConfig> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("configKey").regex(keyword),
                    Criteria.where("configVal").regex(keyword),
                    Criteria.where("configGroup").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("createTime").descending();

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}