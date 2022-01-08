package cn.sixlab.minesoft.singte.module.minesoft.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.module.minesoft.dao.StePoemDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import cn.sixlab.minesoft.singte.module.minesoft.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/poem")
public class AdminPoemController extends BaseController {

    @Autowired
    private StePoemDao poemDao;

    @Autowired
    private PoemService poemService;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/poem/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<StePoem> pageResult = poemDao.selectPoems(keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/poem/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitPoem")
    public ModelResp submitPoem(StePoem stePoem) {
        poemService.addPoem(stePoem);
        return ModelResp.success();
    }

}
