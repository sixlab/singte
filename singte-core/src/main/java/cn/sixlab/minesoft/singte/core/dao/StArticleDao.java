package cn.sixlab.minesoft.singte.core.dao;

import cn.sixlab.minesoft.singte.core.common.pager.BaseDao;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StBeanUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StArticleDao extends BaseDao {

    public void deleteByPrimaryKey(Integer id) {
        mongoTemplate.remove(id);
    }

    public void insert(StArticle record) {
        mongoTemplate.save(record);
    }

    public StArticle selectByPrimaryKey(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, StArticle.class);
    }

    public void updateByPrimaryKeySelective(StArticle record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        StArticle target = mongoTemplate.findOne(query, StArticle.class);
        if (target != null) {
            StBeanUtils.copyProperties(record, target);
            mongoTemplate.save(target);
        }
    }

    public void updateByPrimaryKey(StArticle record) {
        mongoTemplate.save(record);
    }

    public List<StArticle> selectHot(int size) {
        Query query = new Query(Criteria.where("publishStatus").is(StConst.YES))
                .with(Sort.by(Sort.Direction.DESC, "viewCount"))
                .limit(size);
        return mongoTemplate.find(query, StArticle.class);
    }

    public List<StArticle> selectLast(int size) {
        Query query = new Query(Criteria.where("publishStatus").is(StConst.YES))
                .with(Sort.by(Sort.Direction.DESC, "publishTime"))
                .limit(size);
        return mongoTemplate.find(query, StArticle.class);
    }

    public Integer selectMaxId() {
        Query query = new Query(Criteria.where("publishStatus").is(StConst.YES))
                .with(Sort.by(Sort.Direction.DESC, "id"))
                .limit(1);
        StArticle article = mongoTemplate.findOne(query, StArticle.class);
        if (null != article) {
            return article.getId();
        }
        return null;
    }

    public StArticle selectRandom(int max, List<Integer> excludeIds) {
        Query query = new Query(
                Criteria.where("publishStatus").is(StConst.YES)
                        .and("id").gte(Math.random() * max)
                        .and("id").nin(excludeIds)
        )
                .with(Sort.by(Sort.Direction.DESC, "id"))
                .limit(1);
        return mongoTemplate.findOne(query, StArticle.class);
    }

    public PageResult<StArticle> selectByCategory(String category, int pageNum, int pageSize) {
        Criteria criteria = Criteria.where("publishStatus").is(StConst.YES);
        Sort sort = Sort.by(Sort.Direction.DESC, "publishTime");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StArticle.class, pageNum, pageSize);
    }

    public StArticle selectByAlias(String alias) {
        Criteria criteria = Criteria
                .where("publishStatus").is(StConst.YES)
                .and("alias").is(alias);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Query query = new Query(criteria).with(sort).limit(1);

        return mongoTemplate.findOne(query, StArticle.class);
    }

    public void addView(Integer id) {
        StArticle article = selectByPrimaryKey(id);
        article.setViewCount(article.getViewCount() + 1);
        updateByPrimaryKey(article);
    }

    public PageResult<StArticle> selectByDate(Date begin, Date end, int pageNum, int pageSize) {
        Criteria criteria = Criteria.where("publishStatus").is(StConst.YES)
                .and("publishTime").gte(begin)
                .and("publishTime").lt(end);
        Sort sort = Sort.by(Sort.Direction.DESC, "publishTime");

        Query query = new Query(criteria).with(sort);

        return pageQuery(query, StArticle.class, pageNum, pageSize);
    }

    public StArticle selectBySourceUrl(String sourceUrl) {
        Criteria criteria = Criteria.where("sourceUrl").is(sourceUrl);

        Query query = new Query(criteria).limit(1);

        return mongoTemplate.findOne(query, StArticle.class);
    }
}