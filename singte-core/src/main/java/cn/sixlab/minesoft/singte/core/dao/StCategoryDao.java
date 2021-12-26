package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class StCategoryDao extends BaseDao<StCategory> {

    @Override
    public Class<StCategory> entityClass() {
        return StCategory.class;
    }

    /**
     * 根据分类查询分类
     *
     * @param category 分类
     * @return 分类信息
     */
    public StCategory selectByCategory(String category) {
        Query query = new Query(Criteria.where("category").is(category)).with(Sort.by("id"));
        return mongoTemplate.findOne(query, StCategory.class);
    }

    public void updateFlag(String flag) {
        Criteria criteria = Criteria.where("flag").ne(flag);

        Update update = new Update().set("flag", flag);

        mongoTemplate.updateMulti(new Query(criteria), update, StCategory.class);
    }

    public void delWithoutFlag(String flag) {
        Criteria criteria = Criteria.where("flag").ne(flag);

        mongoTemplate.remove(new Query(criteria), StCategory.class);
    }
}