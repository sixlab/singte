package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SteAncientSectionDao extends BaseDao<SteAncientSection> {

    @Override
    public Class<SteAncientSection> entityClass() {
        return SteAncientSection.class;
    }

    public List<SteAncientSection> listBookSections(String bookName) {
        Criteria criteria = Criteria.where("bookName").is(bookName);

        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, entityClass());
    }

    public PageResult<SteAncientSection> selectSections(SteAncientBook ancientBook, String keyword, Integer pageNum, Integer pageSize) {
        Criteria criteria = new Criteria();
        if (null != ancientBook) {
            if (StringUtils.isNotEmpty(ancientBook.getAncientSet())) {
                criteria = criteria.and("ancientSet").is(ancientBook.getAncientSet());
            }
            if (StringUtils.isNotEmpty(ancientBook.getAncientCategory())) {
                criteria = criteria.and("ancientCategory").is(ancientBook.getAncientCategory());
            }
            if (StringUtils.isNotEmpty(ancientBook.getBookName())) {
                criteria = criteria.and("bookName").is(ancientBook.getBookName());
            }
        }

        if (StringUtils.isNotEmpty(keyword)) {
            Criteria keywordCriteria = new Criteria().orOperator(
                    Criteria.where("ancientSet").regex(keyword),
                    Criteria.where("ancientCategory").regex(keyword),
                    Criteria.where("bookName").regex(keyword),
                    Criteria.where("author").regex(keyword),
                    Criteria.where("contentText").regex(keyword),
                    Criteria.where("intro").regex(keyword)
            );

            criteria = criteria.andOperator(keywordCriteria);
        }
        Sort sort = Sort.by("weight");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, entityClass(), pageNum, pageSize);
    }
}