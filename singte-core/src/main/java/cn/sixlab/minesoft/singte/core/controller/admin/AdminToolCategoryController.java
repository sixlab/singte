package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteToolCategoryDao;
import cn.sixlab.minesoft.singte.core.models.SteToolCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/tool/category")
public class AdminToolCategoryController extends BaseController {

    @Autowired
    private SteToolCategoryDao toolCategoryDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/tool/categoryList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<SteToolCategory> pageResult = toolCategoryDao.selectCategories(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/tool/categoryListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitCategory")
    public ModelResp submitCategory(SteToolCategory params) {
        SteToolCategory nextInfo;

        SteToolCategory checkExist = toolCategoryDao.selectByName(params.getCategory());

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = toolCategoryDao.selectById(params.getId());

            if (null == nextInfo) {
                return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
            }

            if (null != checkExist && !params.getId().equals(checkExist.getId())) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo.setUpdateTime(new Date());
        } else {
            if (null != checkExist) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo = new SteToolCategory();
            nextInfo.setCount(0);
            nextInfo.setStatus(StConst.ST_PUBLISH_DID);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setCategory(params.getCategory());
        nextInfo.setWeight(params.getWeight());
        nextInfo.setIntro(params.getIntro());

        toolCategoryDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
        SteToolCategory record = toolCategoryDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        toolCategoryDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        SteToolCategory record = toolCategoryDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        toolCategoryDao.deleteById(id);

        return ModelResp.success();
    }
}
