package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientDao;
import cn.sixlab.minesoft.singte.core.models.SteAncient;
import cn.sixlab.minesoft.singte.core.service.AncientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/admin/ancient")
public class AdminAncientController extends BaseController {

    @Autowired
    private SteAncientDao ancientDao;

    @Autowired
    private AncientService ancientService;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/ancient/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<SteAncient> pageResult = ancientDao.selectAncients(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitAncient")
    public ModelResp submitAncient(SteAncient steAncient, String ancientContent) {
        String[] lines = StringUtils.split(ancientContent, "\r\n");
        steAncient.setAncientLines(Arrays.asList(lines));
        ancientService.addAncient(steAncient);
        return ModelResp.success();
    }

}
