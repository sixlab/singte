package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SteAncientBookDao extends BaseDao<SteAncientBook> {

    @Override
    public Class<SteAncientBook> entityClass() {
        return SteAncientBook.class;
    }

    public List<SteAncientBook> listCategoryBook(String category) {
        Criteria criteria = Criteria.where("ancientCategory").is(category);

        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public SteAncientBook selectByParents(String ancientSet, String ancientCategory, String bookName) {
        Criteria criteria = Criteria.where("ancientSet").is(ancientSet)
                .and("ancientCategory").is(ancientCategory)
                .and("bookName").is(bookName);

        Sort sort = Sort.by("id");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.findOne(query, entityClass());
    }

    public PageResult<SteAncientBook> selectBooks(SteAncientCategory ancientCategory, String keyword, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();

        if (null != ancientCategory) {
            if (StringUtils.isNotEmpty(ancientCategory.getAncientSet())) {
                criteria = criteria.and("ancientSet").is(ancientCategory.getAncientSet());
            }
            if (StringUtils.isNotEmpty(ancientCategory.getAncientCategory())) {
                criteria = criteria.and("ancientCategory").is(ancientCategory.getAncientCategory());
            }
        }

        if (StringUtils.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("ancientSet").regex(keyword),
                    Criteria.where("ancientCategory").regex(keyword),
                    Criteria.where("bookName").regex(keyword),
                    Criteria.where("author").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}