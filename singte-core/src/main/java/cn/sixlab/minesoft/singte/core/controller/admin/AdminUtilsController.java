package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import cn.sixlab.minesoft.singte.core.models.SteAncientSet;
import cn.sixlab.minesoft.singte.core.poetry.PoetryImportApi;
import cn.sixlab.minesoft.singte.core.poetry.PoetryModel;
import cn.sixlab.minesoft.singte.core.service.AncientService;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.nlpcn.commons.lang.jianfan.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/utils")
public class AdminUtilsController extends BaseController {

    @Autowired
    private SteAncientSetDao ancientSetDao;

    @Autowired
    private SteAncientCategoryDao ancientCategoryDao;

    @Autowired
    private SteAncientBookDao ancientBookDao;

    @Autowired
    private SteAncientSectionDao ancientSectionDao;

    @Autowired
    private AncientService ancientService;

    @ResponseBody
    @PostMapping(value = "/simplifySection")
    public ModelResp simplifySection() {

        ancientService.iterSections(new Callback<SteAncientSection, Void>() {
            @Override
            public Void call(SteAncientSection param) {
                param.setAuthor(Converter.SIMPLIFIED.convert(param.getAuthor()));
                param.setBookName(Converter.SIMPLIFIED.convert(param.getBookName()));
                param.setSectionName(Converter.SIMPLIFIED.convert(param.getSectionName()));
                param.setContentHtml(Converter.SIMPLIFIED.convert(param.getContentHtml()));
                param.setContentText(Converter.SIMPLIFIED.convert(param.getContentText()));

                ancientSectionDao.save(param);
                return null;
            }
        });

        return ModelResp.success();
    }

    @ResponseBody
    @PostMapping(value = "/reloadBook")
    public ModelResp reloadBook() {
        ancientService.iterSections(new Callback<SteAncientSection, Void>() {
            @Override
            public Void call(SteAncientSection param) {
                SteAncientBook book = ancientBookDao.selectBook(param.getAncientSet(), param.getAncientCategory(), param.getBookName());

                if (null == book) {
                    book = new SteAncientBook();
                    book.setAncientSet(param.getAncientSet());
                    book.setAncientCategory(param.getAncientCategory());
                    book.setBookName(param.getBookName());
                    book.setAuthor("");
                    book.setCount(0);
                    book.setWeight(0);
                    book.setIntro("");
                    book.setStatus(StConst.YES);
                    book.setCreateTime(new Date());
                    ancientBookDao.save(book);
                }

                return null;
            }
        });

        ancientService.iterBooks(new Callback<SteAncientBook, Void>() {
            @Override
            public Void call(SteAncientBook param) {
                List<SteAncientSection> sectionList = ancientSectionDao.listBookSections(param.getBookName());

                if (CollUtil.isEmpty(sectionList)) {
                    param.setCount(0);
                    param.setStatus(StConst.No);
                    ancientBookDao.save(param);
                } else {
                    List<String> authorList = ancientSectionDao.listBookAuthor(param.getBookName());
                    if (CollUtil.isEmpty(authorList)) {
                        param.setAuthor("-");
                    } else if (authorList.size() == 1) {
                        param.setAuthor(authorList.get(0));
                    } else {
                        param.setAuthor("合著");
                    }
                    param.setCount(sectionList.size());
                    param.setStatus(StConst.YES);
                    ancientBookDao.save(param);
                }

                return null;
            }
        });

        return ModelResp.success();
    }

    @ResponseBody
    @PostMapping(value = "/countAncient")
    public ModelResp countAncient() {
        ancientService.iterSets(new Callback<SteAncientSet, Void>() {
            @Override
            public Void call(SteAncientSet param) {
                String set = param.getAncientSet();
                int count = ancientBookDao.countSet(set);
                param.setCount(count);
                param.setStatus(StConst.YES);
                ancientSetDao.save(param);

                return null;
            }
        });

        ancientService.iterCategories(new Callback<SteAncientCategory, Void>() {
            @Override
            public Void call(SteAncientCategory param) {
                int count = ancientBookDao.countCategory(param.getAncientCategory());
                param.setCount(count);
                param.setStatus(StConst.YES);
                ancientCategoryDao.save(param);
                return null;
            }
        });

        return ModelResp.success();
    }

    @ResponseBody
    @PostMapping(value = "/importAncient")
    public ModelResp importAncient(String type) throws IOException {

        PoetryImportApi poetryApi = SpringUtil.getBean(type, PoetryImportApi.class);
        if (null != poetryApi) {
            poetryApi.parsePoetry();
            return ModelResp.success();
        }else{
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }
    }

    @ResponseBody
    @PostMapping(value = "/importOneAncient")
    public ModelResp importOneAncient(String type, PoetryModel model) throws IOException {
        log.info("type>>>>>>>>");
        PoetryImportApi poetryApi = SpringUtil.getBean(type, PoetryImportApi.class);
        if (null != poetryApi) {
            poetryApi.parseOnePoetry(model);
            return ModelResp.success();
        }else{
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }
    }
}
