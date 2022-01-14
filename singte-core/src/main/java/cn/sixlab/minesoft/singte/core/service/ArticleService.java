package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.I18nUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.dao.StCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.StKeywordDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import cn.sixlab.minesoft.singte.core.models.StCategory;
import cn.sixlab.minesoft.singte.core.models.StKeyword;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.ParseException;
import java.util.*;

@Service
public class ArticleService {

    @Autowired
    private StArticleDao articleDao;

    @Autowired
    private StKeywordDao keywordDao;

    @Autowired
    private StCategoryDao categoryDao;

    public int countCategory() {
        categoryDao.updateFlag("1");

        String flag = "update" + new Date().getTime();

        List<StCategory> categoryList = articleDao.countCategory();
        for (StCategory item : categoryList) {
            StCategory stCategory = categoryDao.selectByCategory(item.getCategory());
            if (null == stCategory) {
                item.setCreateTime(new Date());
                item.setFlag(flag);
                categoryDao.save(item);
            } else {
                stCategory.setUpdateTime(new Date());
                stCategory.setArticleCount(item.getArticleCount());
                stCategory.setFlag(flag);
                categoryDao.save(stCategory);
            }
        }

        categoryDao.delWithoutFlag(flag);

        return categoryList.size();
    }

    public int countKeyword() {
        keywordDao.updateFlag("1");

        String flag = "update" + new Date().getTime();

        int pageNum = 1;
        Map<String, Integer> keywordCount = new HashMap<>();
        while (pageNum > 0) {
            PageResult<StArticle> pageResult = articleDao.selectByCategory("", pageNum, 10);

            List<StArticle> articleList = pageResult.getList();

            for (StArticle article : articleList) {
                List<String> keywords = article.getKeywords();
                for (String keyword : keywords) {
                    StKeyword stKeyword = keywordDao.selectByKeyword(keyword);
                    if (stKeyword == null) {
                        stKeyword = new StKeyword();
                        stKeyword.setKeyword(keyword);
                        stKeyword.setArticleCount(0);
                        stKeyword.setCreateTime(new Date());
                        keywordDao.save(stKeyword);
                    }
                    String keywordId = stKeyword.getId();
                    int count = keywordCount.getOrDefault(keywordId, 0) + 1;
                    keywordCount.put(keywordId, count);
                }
                articleDao.save(article);
            }

            if (pageResult.isHasNext()) {
                pageNum++;
            } else {
                pageNum = 0;
            }
        }

        keywordCount.forEach((key, val) -> {
            StKeyword stKeyword = keywordDao.selectById(key);
            stKeyword.setArticleCount(val);
            stKeyword.setUpdateTime(new Date());
            stKeyword.setFlag(flag);
            keywordDao.save(stKeyword);
        });

        keywordDao.delWithoutFlag(flag);

        return keywordCount.size();
    }

    public PageResult<StArticle> list(Integer pageNum, Integer pageSize) {
        return articleDao.selectArticles(null, StConst.YES, pageNum, pageSize);
    }

    /**
     * 查询最热门的文章
     *
     * @param size 数量
     * @return 文章列表
     */
    public List<StArticle> topHot(int size) {
        return articleDao.selectHot(size);
    }

    /**
     * 查询最新文章
     *
     * @param size 数量
     * @return 文章列表
     */
    public List<StArticle> topLast(int size) {
        return articleDao.selectLast(size);
    }

    /**
     * 随机查询文章
     *
     * @param size 数量
     * @return 文章列表
     */
    public List<StArticle> random(int size) {
        return articleDao.selectRandom(size);
    }

    public List<StArticle> topView(int size) {
        return null;
    }

    public void listParam(ModelMap modelMap, Integer pageNum, Integer pageSize, String pageType, String uriPrefix) {
        modelMap.put("pageNum", pageNum);
        modelMap.put("pageSize", pageSize);
        modelMap.put("pageType", pageType);
        modelMap.put("pageUri", uriPrefix);
    }

    public void listList(ModelMap modelMap, Integer pageNum, Integer pageSize) {
        listParam(modelMap, pageNum, pageSize, "list", "/list?pageNum=");
        modelMap.put("title", I18nUtils.get("title.list"));
    }

    public void listCategory(ModelMap modelMap, String category, Integer pageNum, Integer pageSize) {
        listParam(modelMap, pageNum, pageSize, "category", "/category/" + category + "?pageNum=");
        modelMap.put("word", category);
        modelMap.put("title", I18nUtils.get("title.category", category));
    }

    public void listKeyword(ModelMap modelMap, String keyword, Integer pageNum, Integer pageSize) {
        listParam(modelMap, pageNum, pageSize, "keyword", "/keyword/" + keyword + "?pageNum=");
        modelMap.put("word", keyword);
        modelMap.put("title", I18nUtils.get("title.keyword", keyword));
    }

    public void listSearch(ModelMap modelMap, String word, Integer pageNum, Integer pageSize) {
        listParam(modelMap, pageNum, pageSize, "search", "/search?" + word + "&pageNum=");
        modelMap.put("word", word);
        modelMap.put("title", I18nUtils.get("title.search", word));
    }

    public void listDate(ModelMap modelMap, String date, Integer pageNum, Integer pageSize) {
        listParam(modelMap, pageNum, pageSize, "date", "/date/" + date + "?pageNum=");
        modelMap.put("date", date);
        modelMap.put("title", I18nUtils.get("title.date", date));
    }

    /**
     * 查询指定日期文章，含有分页
     *
     * @param date 日期，yyyyMMdd 格式
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 文章列表(分页)
     */
    public PageResult<StArticle> selectByDate(String date, int pageNum, int pageSize) {

        Date begin = null;
        if (StringUtils.isNotEmpty(date)) {
            try {
                begin = DateUtils.parseDate(date, "yyyyMMdd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (null == begin) {
            begin = new Date();
        }

        begin = DateUtils.truncate(begin, Calendar.DAY_OF_MONTH);
        Date end = DateUtils.addDays(begin, 1);

        PageResult<StArticle> articleList = articleDao.selectByDate(begin, end, pageNum, pageSize);

        return articleList;
    }

    /**
     * 查询指定分类文章，含有分页
     *
     * @param category 分类
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 文章列表(分页)
     */
    public PageResult<StArticle> selectCategory(String category, int pageNum, int pageSize) {
        PageResult<StArticle> articleList = articleDao.selectByCategory(category, pageNum, pageSize);

        return articleList;
    }

    public PageResult<StArticle> selectKeyword(String keyword, int pageNum, int pageSize) {
        PageResult<StArticle> articleList = articleDao.selectByKeyword(keyword, pageNum, pageSize);

        return articleList;
    }

    public PageResult<StArticle> selectSearch(String word, int pageNum, int pageSize) {
        PageResult<StArticle> articleList = articleDao.selectByWord(word, pageNum, pageSize);

        return articleList;
    }
}
