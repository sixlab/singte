package cn.sixlab.minesoft.singte.core.dao;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.StUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class StUserDao extends BaseDao<StUser> {

    @Override
    public Class<StUser> entityClass() {
        return StUser.class;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    public StUser selectByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));

        return mongoTemplate.findOne(query, StUser.class);
    }

    public StUser selectByToken(String token) {
        Query query = new Query(Criteria.where("token").is(token));

        return mongoTemplate.findOne(query, StUser.class);
    }

    public PageResult<StUser> selectUsers(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StrUtil.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("username").regex(keyword),
                    Criteria.where("showName").regex(keyword),
                    Criteria.where("mobile").regex(keyword),
                    Criteria.where("email").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("createTime").descending();

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StUser.class, pageNum, pageSize);
    }
}