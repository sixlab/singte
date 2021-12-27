package cn.sixlab.minesoft.singte.core.common.config;

import cn.sixlab.minesoft.singte.core.common.pager.PageHelper;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;

public abstract class BaseDao<T extends BaseModel> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    public abstract Class<T> entityClass();

    public T save(T record) {
        mongoTemplate.save(record);
        return record;
    }

    public T selectById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, entityClass());
    }

    public void updateSelective(T record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        T target = mongoTemplate.findOne(query, entityClass());
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, entityClass());
    }

    public PageResult<T> pageQuery(Query query, Class<T> clz, int pageNum, int pageSize) {
        long totalNum = mongoTemplate.count(query, clz);
        int totalPages = (int) Math.ceil(totalNum / (double) pageSize);

        List<T> content;
        if (totalNum > 0) {
            if (pageNum <= 0) {
                pageNum = 1;
            } else if (pageNum > totalPages) {
                pageNum = totalPages;
            }

            query = query.skip((pageNum - 1) * pageSize).limit(pageSize);
            content = mongoTemplate.find(query, clz);
        } else {
            content = Collections.emptyList();
        }

        return PageHelper.pager(content, pageNum, pageSize, totalNum, totalPages);
    }

}
