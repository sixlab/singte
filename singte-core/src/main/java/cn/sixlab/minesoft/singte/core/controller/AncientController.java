package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import cn.sixlab.minesoft.singte.core.models.SteAncientSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ancient")
public class AncientController extends BaseController {

    @Autowired
    private SteAncientSetDao ancientSetDao;

    @Autowired
    private SteAncientCategoryDao ancientCategoryDao;

    @Autowired
    private SteAncientBookDao ancientBookDao;

    @Autowired
    private SteAncientSectionDao ancientSectionDao;

    @GetMapping(value = "/set")
    public String listSet(ModelMap modelMap) {

        modelMap.put("result", ancientSetDao.list());

        return "ancient/set";
    }

    @GetMapping(value = "/category/{setId}")
    public String listCategory(ModelMap modelMap, @PathVariable String setId) {
        SteAncientSet ancientSet = ancientSetDao.selectById(setId);

        String setAncientText = ancientSet.getAncientSet();

        modelMap.put("title", setAncientText);
        modelMap.put("result", ancientCategoryDao.listSetCategory(setAncientText));

        return "ancient/category";
    }

    @GetMapping(value = "/book/{categoryId}")
    public String listBook(ModelMap modelMap, @PathVariable String categoryId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
        SteAncientCategory ancientCategory = ancientCategoryDao.selectById(categoryId);
        String ancientSet = ancientCategory.getAncientSet();

        SteAncientSet steAncientSet = ancientSetDao.selectByName(ancientSet);

        modelMap.put("ancientSet", steAncientSet);

        String category = ancientCategory.getAncientCategory();
        modelMap.put("title", category);
        modelMap.put("result", ancientBookDao.queryBook(ancientCategory, null, pageNum, pageSize));

        return "ancient/book";
    }

    @GetMapping(value = "/section/{bookId}")
    public String listSection(ModelMap modelMap, @PathVariable String bookId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
        SteAncientBook ancientBook = ancientBookDao.selectById(bookId);

        SteAncientSet ancientSet = ancientSetDao.selectByName(ancientBook.getAncientSet());
        SteAncientCategory ancientCategory = ancientCategoryDao.selectBySetAndName(ancientBook.getAncientSet(), ancientBook.getAncientCategory());

        modelMap.put("ancientSet", ancientSet);
        modelMap.put("ancientCategory", ancientCategory);

        String bookName = ancientBook.getBookName();
        modelMap.put("title", bookName);
        modelMap.put("result", ancientSectionDao.querySection(ancientBook, null, pageNum, pageSize));

        return "ancient/section";
    }

    @GetMapping(value = "/content/{sectionId}")
    public String content(ModelMap modelMap, @PathVariable String sectionId) {
        SteAncientSection ancientSection = ancientSectionDao.selectById(sectionId);

        SteAncientSet ancientSet = ancientSetDao.selectByName(ancientSection.getAncientSet());
        SteAncientCategory ancientCategory = ancientCategoryDao.selectBySetAndName(ancientSection.getAncientSet(), ancientSection.getAncientCategory());
        SteAncientBook ancientBook = ancientBookDao.selectBook(ancientSection.getAncientSet(), ancientSection.getAncientCategory(), ancientSection.getBookName());

        modelMap.put("setId", ancientSet.getId());
        modelMap.put("categoryId", ancientCategory.getId());
        modelMap.put("bookId", ancientBook.getId());

        modelMap.put("title", ancientSection.getSectionName());
        modelMap.put("ancientSection", ancientSection);

        return "ancient/content";
    }

    @ResponseBody
    @PostMapping(value = "/thumb")
    public ModelResp thumb(String sectionId) {
        SteAncientSection ancientSection = ancientSectionDao.selectById(sectionId);
        ancientSection.setThumbCount(ancientSection.getThumbCount() + 1);
        ancientSectionDao.save(ancientSection);
        return ModelResp.success(ancientSection.getThumbCount());
    }
}
