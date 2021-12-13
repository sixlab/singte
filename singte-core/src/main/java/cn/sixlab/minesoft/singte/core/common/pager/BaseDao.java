package cn.sixlab.minesoft.singte.core.common.pager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;

public class BaseDao {

    @Autowired
    protected MongoTemplate mongoTemplate;

    public <T> PageResult<T> pageQuery(Query query, Class<T> clz, int pageNum, int pageSize) {
        long totalNum = mongoTemplate.count(query, clz);
        int totalPages = (int) Math.ceil(totalNum / (double) pageSize);

        List<T> content;
        if (totalNum > 0) {
            if (pageNum <= 0) {
                pageNum = 1;
            } else if (pageNum > totalPages) {
                pageNum = totalPages;
            }

            query = query.skip((pageNum - 1) * pageSize).limit(pageSize);
            content = mongoTemplate.find(query, clz);
        } else {
            content = Collections.emptyList();
        }

        return PageHelper.pager(content, pageNum, pageSize, totalNum, totalPages);
    }

}
