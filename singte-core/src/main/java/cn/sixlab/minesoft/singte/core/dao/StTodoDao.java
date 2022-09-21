package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StTodo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StTodoDao extends BaseDao<StTodo> {

    @Override
    public Class<StTodo> entityClass() {
        return StTodo.class;
    }

    @Override
    public StTodo selectExist(StTodo record) {
        Criteria criteria = Criteria.where("todoCode").is(record.getTodoCode());

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<StTodo> queryData(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("todoCode").regex(keyword),
                    Criteria.where("todoName").regex(keyword),
                    Criteria.where("username").regex(keyword),
                    Criteria.where("todoCron").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }

    public List<StTodo> selectStatus(String username, String status) {
        Criteria criteria = Criteria.where("status").is(status);

        if(StrUtil.isNotEmpty(username)){
            criteria = criteria.and("username").is(username);
        }

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public StTodo selectStatusByUserNo(String username, Integer indexNo, String status) {
        Criteria criteria = Criteria.where("status").is(status)
                .and("username").is(username).and("indexNo").is(indexNo);

        Sort sort = Sort.by("weight", "id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

}