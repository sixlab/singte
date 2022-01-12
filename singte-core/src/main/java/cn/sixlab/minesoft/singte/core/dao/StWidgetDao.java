package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.models.StWidget;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StWidgetDao extends BaseDao<StWidget> {

    @Override
    public Class<StWidget> entityClass() {
        return StWidget.class;
    }

    /**
     * 查询启用的插件
     * 
     * @param status
     * @return
     */
    public List<StWidget> selectByStatus(String status) {
        Query query = new Query(Criteria.where("status").is(status)).with(Sort.by("weight"));
        return mongoTemplate.find(query, entityClass());
    }

    public StWidget selectEnableWidget(String widgetCode){
        Criteria criteria = Criteria.where("status").is(StConst.YES)
                .and("widgetCode").is(widgetCode);

        Query query = new Query(criteria).with(Sort.by("weight"));

        return mongoTemplate.findOne(query, entityClass());
    }

    public StWidget selectByCode(String widgetCode) {
        Criteria criteria = Criteria.where("widgetCode").is(widgetCode);

        Query query = new Query(criteria).with(Sort.by("weight"));

        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<StWidget> selectWidgets(String keyword, String status, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(status)) {
            criteria = criteria.and("status").is(status);
        }

        if (StringUtils.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("widgetName").regex(keyword),
                    Criteria.where("widgetCode").regex(keyword),
                    Criteria.where("widgetIntro").regex(keyword),
                    Criteria.where("widgetParam").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}