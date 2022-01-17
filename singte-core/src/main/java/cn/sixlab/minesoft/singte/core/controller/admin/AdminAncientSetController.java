package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientSet;
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

    @GetMapping(value = "/list")
    public String list() {
        return "admin/ancient/setList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<SteAncientSet> pageResult = ancientSetDao.selectAncientSets(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/setListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitSet")
    public ModelResp submitSet(SteAncientSet steAncientSet) {
        steAncientSet.setCount(0);
        steAncientSet.setCreateTime(new Date());
        ancientSetDao.save(steAncientSet);
        return ModelResp.success();
    }

}
