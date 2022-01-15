package cn.sixlab.minesoft.singte.core.controller;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/ancient/section")
public class AdminAncientSectionController extends BaseController {

    @Autowired
    private SteAncientSectionDao ancientSectionDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/ancient/sectionList";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<SteAncientSection> pageResult = ancientSectionDao.selectSections(null, keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/sectionListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitSection")
    public ModelResp submitSection(SteAncientSection ancientSection) {
        String[] lines = StringUtils.split(ancientSection.getContentText(), "\r\n");
        ancientSection.setContentHtml(StrUtil.join("<br />", lines));
        ancientSection.setContentText(StrUtil.join("", lines));
        ancientSection.setViewCount(0);
        ancientSection.setCreateTime(new Date());
        ancientSectionDao.save(ancientSection);
        return ModelResp.success();
    }

}
