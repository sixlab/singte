package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/ancient/book")
public class AdminAncientBookController extends BaseController {

    @Autowired
    private SteAncientBookDao ancientBookDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/ancient/bookList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<SteAncientBook> pageResult = ancientBookDao.selectBooks(null, keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/bookListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitBook")
    public ModelResp submitBook(SteAncientBook params) {
        SteAncientBook nextInfo;
        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = ancientBookDao.selectById(params.getId());

            if (null == nextInfo) {
                return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
            }

            nextInfo.setUpdateTime(new Date());
        } else {
            SteAncientBook checkExist = ancientBookDao.selectBook(params.getAncientSet(), params.getAncientCategory(), params.getBookName());
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
    @RequestMapping(value = "/submitStatus")
    public ModelResp submitStatus(String id, String status) {
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
}
