package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StMenuDao extends BaseDao<StMenu> {

    @Override
    public Class<StMenu> entityClass() {
        return StMenu.class;
    }

    /**
     * 查询分组下所有菜单，根据权重排序
     * @param menuGroup 分组
     * @return 菜单列表
     */
    public List<StMenu> selectGroupMenus(String menuGroup) {
        Query query = new Query(Criteria.where("menuGroup").is(menuGroup)).with(Sort.by("weight"));
        return mongoTemplate.find(query, StMenu.class);
    }
}