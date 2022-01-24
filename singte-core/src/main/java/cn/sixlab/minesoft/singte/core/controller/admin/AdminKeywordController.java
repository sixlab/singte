package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StKeywordDao;
import cn.sixlab.minesoft.singte.core.models.StKeyword;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/keyword")
public class AdminKeywordController extends BaseController {

    @Autowired
    private StKeywordDao keywordDao;

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/reload")
    public ModelResp reload() {
        int keywordSize = articleService.countKeyword();
        return ModelResp.success(keywordSize);
    }

    @GetMapping(value = "/list")
    public String list() {
        return "admin/keyword/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StKeyword> pageResult = keywordDao.queryKeyword(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/keyword/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submit")
    public ModelResp submit(StKeyword params) {
        StKeyword nextInfo;

        StKeyword checkExist = keywordDao.selectByKeyword(params.getKeyword());

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = keywordDao.selectById(params.getId());

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

            nextInfo = new StKeyword();
            nextInfo.setCount(0);
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setKeyword(params.getKeyword());
        nextInfo.setWeight(params.getWeight());
        nextInfo.setIntro(params.getIntro());

        keywordDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public ModelResp status(String id, String status) {
        StKeyword record = keywordDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        keywordDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        StKeyword record = keywordDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        keywordDao.deleteById(id);

        return ModelResp.success();
    }
}
