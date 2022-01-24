package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StPageDao;
import cn.sixlab.minesoft.singte.core.models.StPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/page")
public class AdminPageController extends BaseController {

    @Autowired
    private StPageDao pageDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/page/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StPage> pageResult = pageDao.queryPage(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/page/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submit")
    public ModelResp submit(StPage params) {
        StPage nextInfo;

        StPage checkExist = pageDao.selectByAlias(params.getAlias());

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = pageDao.selectById(params.getId());

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

            nextInfo = new StPage();
            nextInfo.setViewCount(0);
            nextInfo.setStatus(StConst.ST_PUBLISH_DID);
            nextInfo.setPublishTime(new Date());
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setAlias(params.getAlias());
        nextInfo.setTitle(params.getTitle());
        nextInfo.setAuthor(params.getAuthor());
        nextInfo.setContent(params.getContent());

        pageDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public ModelResp status(String id, String status) {
        StPage record = pageDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        pageDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        StPage record = pageDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        pageDao.deleteById(id);

        return ModelResp.success();
    }
}
