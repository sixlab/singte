package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.dao.StCategoryDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private StArticleDao articleMapper;

    @Autowired
    private StCategoryDao categoryMapper;

    public List<StArticle> topHot(int size) {
        return articleMapper.selectHot(size);
    }

    public List<StArticle> topLast(int size) {
        return articleMapper.selectLast(size);
    }

    public List<StArticle> random(int size) {
        List<StArticle> result = new ArrayList<>();

        Integer maxId = articleMapper.selectMaxId();
        if (null != maxId) {
            List<Integer> excludeIds = new ArrayList<>();

            int retry = 0;
            while (size > 0) {
                StArticle article = articleMapper.selectRandom(maxId, excludeIds);
                if (article != null) {
                    result.add(article);
                    excludeIds.add(article.getId());
                    retry = 0;
                    size--;
                } else {
                    retry++;

                    if (retry >= 5) {
                        break;
                    }
                }
            }
        }

        return result;
    }

    public void listParam(ModelMap modelMap,  Integer pageNum, Integer pageSize, String pageType, String uriPrefix) {
        modelMap.put("pageNum", pageNum);
        modelMap.put("pageSize", pageSize);
        modelMap.put("pageType", pageType);
        modelMap.put("pageUri", uriPrefix);
    }

    public boolean listCategory(ModelMap modelMap, Integer categoryId, Integer pageNum, Integer pageSize) {
        StCategory stCategory = categoryMapper.selectByPrimaryKey(categoryId);

        if (null != stCategory) {
            listParam(modelMap, pageNum, pageSize, "category", "/category/" + categoryId);
            modelMap.put("category", stCategory.getCategory());

            return true;
        }
        return false;
    }

    public List<StArticle> selectByDate(String date, int pageNum, int pageSize) {

        Date begin = null;
        if(StringUtils.isNotEmpty(date)){
            try {
                begin = DateUtils.parseDate(date, "yyyyMMdd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(null==begin){
            begin = new Date();
        }

        Date end = DateUtils.addDays(begin, 1);

        List<StArticle> articleList = articleMapper.selectByDate(begin, end, pageNum, pageSize);

        return articleList;
    }

    public List<StArticle> selectCategory(String category, int pageNum, int pageSize) {
        List<StArticle> articleList = articleMapper.selectByCategory(category, pageNum, pageSize);

        return articleList;
    }
}
