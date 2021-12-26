package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.I18nUtils;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.dao.StCategoryDao;
import cn.sixlab.minesoft.singte.core.models.StArticle;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private StArticleDao articleMapper;

    @Autowired
    private StCategoryDao categoryMapper;

    /**
     * 查询最热门的文章
     *
     * @param size 数量
     * @return 文章列表
     */
    public List<StArticle> topHot(int size) {
        return articleMapper.selectHot(size);
    }

    /**
     * 查询最新文章
     *
     * @param size 数量
     * @return 文章列表
     */
    public List<StArticle> topLast(int size) {
        return articleMapper.selectLast(size);
    }

    /**
     * 随机查询文章
     *
     * @param size 数量
     * @return 文章列表
     */
    public List<StArticle> random(int size) {
        return articleMapper.selectRandom(size);
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

        PageResult<StArticle> articleList = articleMapper.selectByDate(begin, end, pageNum, pageSize);

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
        PageResult<StArticle> articleList = articleMapper.selectByCategory(category, pageNum, pageSize);

        return articleList;
    }

    public PageResult<StArticle> selectKeyword(String keyword, int pageNum, int pageSize) {
        PageResult<StArticle> articleList = articleMapper.selectByKeyword(keyword, pageNum, pageSize);

        return articleList;
    }

    public PageResult<StArticle> selectSearch(String word, int pageNum, int pageSize) {
        PageResult<StArticle> articleList = articleMapper.selectByWord(word, pageNum, pageSize);

        return articleList;
    }
}
