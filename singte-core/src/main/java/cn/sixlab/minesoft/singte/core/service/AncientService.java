package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import cn.sixlab.minesoft.singte.core.models.SteAncientSet;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AncientService {
    private final int pageSize = 100;

    @Autowired
    private SteAncientSetDao setDao;

    @Autowired
    private SteAncientCategoryDao categoryDao;

    @Autowired
    private SteAncientBookDao bookDao;

    @Autowired
    private SteAncientSectionDao sectionDao;

    public void iterSets(Callback<SteAncientSet, Void> callback) {
        int pageNum = 0;
        boolean hasNex = true;

        while (hasNex) {
            pageNum++;
            PageResult<SteAncientSet> pageResult = setDao.queryData(null, null, pageNum, pageSize);

            for (SteAncientSet set : pageResult.getList()) {
                callback.call(set);
            }

            hasNex = pageResult.isHasNext();
        }
    }

    public void iterCategories(Callback<SteAncientCategory, Void> callback) {
        int pageNum = 0;
        boolean hasNex = true;

        while (hasNex) {
            pageNum++;
            PageResult<SteAncientCategory> pageResult = categoryDao.queryData(null, null, pageNum, pageSize);

            for (SteAncientCategory category : pageResult.getList()) {
                callback.call(category);
            }

            hasNex = pageResult.isHasNext();
        }
    }

    public void iterBooks(Callback<SteAncientBook, Void> callback) {
        int pageNum = 0;
        boolean hasNex = true;

        while (hasNex) {
            pageNum++;
            PageResult<SteAncientBook> pageResult = bookDao.queryBook(null, null, pageNum, pageSize);

            for (SteAncientBook book : pageResult.getList()) {
                callback.call(book);
            }

            hasNex = pageResult.isHasNext();
        }
    }

    public void iterSections(Callback<SteAncientSection, Void> callback) {
        int pageNum = 0;
        boolean hasNex = true;

        while (hasNex) {
            pageNum++;
            PageResult<SteAncientSection> pageResult = sectionDao.querySection(null, null, pageNum, pageSize);

            for (SteAncientSection section : pageResult.getList()) {
                callback.call(section);
            }

            hasNex = pageResult.isHasNext();
        }
    }
}
