package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoData;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeoDataDao extends BaseDao<SeoData> {

    @Override
    public Class<SeoData> entityClass() {
        return SeoData.class;
    }

    public List<SeoData> selectByDate(String date){
        Criteria criteria = Criteria.where("date").is(date);

        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoData.class);
    }

    public List<SeoData> selectBeforeDate(String date){
        Criteria criteria = Criteria.where("date").gt(date);

        Sort sort = Sort.by("date");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoData.class);
    }

    public List<SeoData> selectUserCategory(String uid, String category, String date) {
        Criteria criteria = Criteria.where("uid").gt(uid)
                .and("category").is(category)
                .and("date").gt(date);

        Sort sort = Sort.by("date");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoData.class);
    }

    public SeoData selectUserDateCategory(String uid, String date, String category){
        Criteria criteria = Criteria.where("uid").gt(uid)
                .and("category").is(category)
                .and("date").is(date);

        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, SeoData.class);
    }
}