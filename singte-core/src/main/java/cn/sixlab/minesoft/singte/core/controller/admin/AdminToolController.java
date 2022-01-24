package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteToolItemDao;
import cn.sixlab.minesoft.singte.core.models.SteToolItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/tool")
public class AdminToolController extends BaseController {

    @Autowired
    private SteToolItemDao toolItemDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/tool/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<SteToolItem> pageResult = toolItemDao.queryTool(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/tool/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitTool")
    public ModelResp submitSet(SteToolItem params) {
        SteToolItem nextInfo;
        SteToolItem checkExist = toolItemDao.selectByCode(params.getToolCode());

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = toolItemDao.selectById(params.getId());

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

            nextInfo = new SteToolItem();
            nextInfo.setViewCount(0);
            nextInfo.setThumbCount(0);
            nextInfo.setStatus(StConst.ST_PUBLISH_DID);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setCategory(params.getCategory());
        nextInfo.setToolName(params.getToolName());
        nextInfo.setToolCode(params.getToolCode());
        nextInfo.setWeight(params.getWeight());
        nextInfo.setIntro(params.getIntro());

        toolItemDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
        SteToolItem record = toolItemDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        toolItemDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        SteToolItem record = toolItemDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        toolItemDao.deleteById(id);

        return ModelResp.success();
    }
}
