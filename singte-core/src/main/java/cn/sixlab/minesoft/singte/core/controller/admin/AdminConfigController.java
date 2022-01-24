package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StConfigDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/config")
public class AdminConfigController extends BaseController {

    @Autowired
    private StConfigDao configDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/config/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StConfig> pageResult = configDao.queryData(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/config/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submit")
    public ModelResp submit(StConfig params) {
        StConfig nextInfo;

        StConfig checkExist = configDao.selectExist(params);

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = configDao.selectById(params.getId());

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

            nextInfo = new StConfig();
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setConfigGroup(params.getConfigGroup());
        nextInfo.setConfigKey(params.getConfigKey());
        nextInfo.setConfigVal(params.getConfigVal());
        nextInfo.setIntro(params.getIntro());

        configDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public ModelResp status(String id, String status) {
        StConfig record = configDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        configDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        StConfig record = configDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        configDao.deleteById(id);

        return ModelResp.success();
    }
}
