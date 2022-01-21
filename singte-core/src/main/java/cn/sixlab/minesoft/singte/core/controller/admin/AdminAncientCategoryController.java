package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import cn.sixlab.minesoft.singte.core.service.AncientService;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/ancient/category")
public class AdminAncientCategoryController extends BaseController {

    @Autowired
    private SteAncientCategoryDao ancientCategoryDao;

    @Autowired
    private SteAncientBookDao ancientBookDao;

    @Autowired
    private AncientService ancientService;

    @ResponseBody
    @PostMapping(value = "/reload")
    public ModelResp reload() {
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

    @GetMapping(value = "/list")
    public String list() {
        return "admin/ancient/categoryList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<SteAncientCategory> pageResult = ancientCategoryDao.selectAncientCategory(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/categoryListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitCategory")
    public ModelResp submitCategory(SteAncientCategory params) {
        SteAncientCategory nextInfo;
        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = ancientCategoryDao.selectById(params.getId());

            if (null == nextInfo) {
                return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
            }

            nextInfo.setUpdateTime(new Date());
        } else {
            SteAncientCategory checkExist = ancientCategoryDao.selectBySetAndName(params.getAncientSet(), params.getAncientCategory());
            if (null != checkExist) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo = new SteAncientCategory();
            nextInfo.setCount(0);
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setAncientSet(params.getAncientSet());
        nextInfo.setAncientCategory(params.getAncientCategory());
        nextInfo.setWeight(params.getWeight());
        nextInfo.setIntro(params.getIntro());

        ancientCategoryDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
        SteAncientCategory record = ancientCategoryDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        ancientCategoryDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        SteAncientCategory record = ancientCategoryDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

}
