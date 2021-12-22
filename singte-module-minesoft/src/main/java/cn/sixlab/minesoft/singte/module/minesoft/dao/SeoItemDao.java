package cn.sixlab.minesoft.singte.module.minesoft.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeoItemDao extends BaseDao<SeoItem> {

    @Override
    public Class<SeoItem> entityClass() {
        return SeoItem.class;
    }

    public List<SeoItem> selectItems(String dataType){
        Criteria criteria = Criteria.where("dataType").is(dataType);

        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoItem.class);
    }

    public List<SeoItem> selectByCategory(String category){
        Criteria criteria = Criteria.where("category").is(category);

        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, SeoItem.class);
    }

    public SeoItem selectUser(String dataType, String uid){
        Criteria criteria = Criteria.where("uid").is(uid)
                .and("dataType").is(dataType);

        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, SeoItem.class);
    }

}