package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StSpiderDao extends BaseDao<StSpider> {

    @Override
    public Class<StSpider> entityClass() {
        return StSpider.class;
    }

    /**
     * 查询已经启用的爬虫任务
     * 
     * @param status
     * @return
     */
    public List<StSpider> selectByStatus(String status) {
        Query query = new Query(Criteria.where("status").is(status)).with(Sort.by("id"));
        return mongoTemplate.find(query, StSpider.class);
    }
}