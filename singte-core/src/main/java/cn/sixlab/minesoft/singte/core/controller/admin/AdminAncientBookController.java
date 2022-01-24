package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import cn.sixlab.minesoft.singte.core.service.AncientService;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/ancient/book")
public class AdminAncientBookController extends BaseController {

    @Autowired
    private SteAncientBookDao ancientBookDao;

    @Autowired
    private SteAncientSectionDao ancientSectionDao;

    @Autowired
    private AncientService ancientService;

    @ResponseBody
    @PostMapping(value = "/reload")
    public ModelResp reload() {
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

    @GetMapping(value = "/list")
    public String list() {
        return "admin/ancient/bookList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<SteAncientBook> pageResult = ancientBookDao.queryData(keyword, status,pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/bookListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submit")
    public ModelResp submit(SteAncientBook params) {
        SteAncientBook nextInfo;
        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = ancientBookDao.selectById(params.getId());

            if (null == nextInfo) {
                return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
            }

            nextInfo.setUpdateTime(new Date());
        } else {
            SteAncientBook checkExist = ancientBookDao.selectExist(params);
            if (null != checkExist) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo = new SteAncientBook();
            nextInfo.setCount(0);
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setAncientSet(params.getAncientSet());
        nextInfo.setAncientCategory(params.getAncientCategory());
        nextInfo.setBookName(params.getBookName());
        nextInfo.setAuthor(params.getAuthor());
        nextInfo.setWeight(params.getWeight());
        nextInfo.setIntro(params.getIntro());

        ancientBookDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public ModelResp status(String id, String status) {
        SteAncientBook record = ancientBookDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        ancientBookDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        SteAncientBook record = ancientBookDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        ancientBookDao.deleteById(id);

        return ModelResp.success();
    }
}
