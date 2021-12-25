package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.models.StUser;
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
}