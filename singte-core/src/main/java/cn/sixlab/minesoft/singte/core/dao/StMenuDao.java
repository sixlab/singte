package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import org.apache.commons.lang3.StringUtils;
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
     *
     * @param menuGroup 分组
     * @return 菜单列表
     */
    public List<StMenu> selectGroupMenus(String menuGroup) {
        Criteria criteria = Criteria.where("menuGroup").is(menuGroup)
                .and("status").is(StConst.YES);

        Query query = new Query(criteria).with(Sort.by("weight"));
        return mongoTemplate.find(query, StMenu.class);
    }

    public PageResult<StMenu> selectMenus(String keyword, String status, int pageNum, int pageSize) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StringUtils.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("menuCode").regex(keyword),
                    Criteria.where("menuLink").regex(keyword),
                    Criteria.where("menuGroup").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StMenu.class, pageNum, pageSize);
    }
}