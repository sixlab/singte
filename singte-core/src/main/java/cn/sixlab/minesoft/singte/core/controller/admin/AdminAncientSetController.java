package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientSet;
import cn.sixlab.minesoft.singte.core.service.AncientService;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/ancient/set")
public class AdminAncientSetController extends BaseController {

    @Autowired
    private SteAncientSetDao ancientSetDao;

    @Autowired
    private SteAncientBookDao ancientBookDao;

    @Autowired
    private AncientService ancientService;

    @ResponseBody
    @PostMapping(value = "/reload")
    public ModelResp reload() {
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

        return ModelResp.success();
    }

    @GetMapping(value = "/list")
    public String list() {
        return "admin/ancient/setList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<SteAncientSet> pageResult = ancientSetDao.queryAncientSet(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/setListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submit")
    public ModelResp submit(SteAncientSet params) {
        SteAncientSet nextInfo;
        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = ancientSetDao.selectById(params.getId());

            if (null == nextInfo) {
                return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
            }

            nextInfo.setUpdateTime(new Date());
        } else {
            SteAncientSet checkExist = ancientSetDao.selectByName(params.getAncientSet());
            if (null != checkExist) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo = new SteAncientSet();
            nextInfo.setCount(0);
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setAncientSet(params.getAncientSet());
        nextInfo.setWeight(params.getWeight());
        nextInfo.setIntro(params.getIntro());

        ancientSetDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public ModelResp status(String id, String status) {
        SteAncientSet record = ancientSetDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        ancientSetDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        SteAncientSet record = ancientSetDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        ancientSetDao.deleteById(id);

        return ModelResp.success();
    }

}

