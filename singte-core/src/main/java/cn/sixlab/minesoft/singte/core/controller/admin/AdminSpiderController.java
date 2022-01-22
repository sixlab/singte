package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StSpiderDao;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/spider")
public class AdminSpiderController extends BaseController {

    @Autowired
    private StSpiderDao spiderDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/spider/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StSpider> pageResult = spiderDao.selectSpiders(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/spider/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitSpider")
    public ModelResp submitSpider(StSpider params) {
        StSpider nextInfo;

        StSpider checkExist = spiderDao.selectByName(params.getSpiderName());

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = spiderDao.selectById(params.getId());

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

            nextInfo = new StSpider();
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setSpiderType(params.getSpiderType());
        nextInfo.setSpiderName(params.getSpiderName());
        nextInfo.setStartUrl(params.getStartUrl());
        nextInfo.setPagerRule(params.getPagerRule());
        nextInfo.setLinkRule(params.getLinkRule());
        nextInfo.setTitleRule(params.getTitleRule());
        nextInfo.setContentRule(params.getContentRule());
        nextInfo.setSummaryRule(params.getSummaryRule());
        nextInfo.setCategoryRule(params.getCategoryRule());
        nextInfo.setKeywordRule(params.getKeywordRule());
        nextInfo.setWaitTimes(params.getWaitTimes());
        nextInfo.setUrlParam(params.getUrlParam());

        spiderDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
        StSpider record = spiderDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        spiderDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        StSpider record = spiderDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        spiderDao.deleteById(id);

        return ModelResp.success();
    }
}
