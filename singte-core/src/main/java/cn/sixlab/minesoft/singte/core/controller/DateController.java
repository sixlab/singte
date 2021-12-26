package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/date")
public class DateController extends BaseController {

    @Autowired
    private ArticleService service;

    @GetMapping(value = "/{date}")
    public String date(ModelMap modelMap, @PathVariable String date) {
        service.listDate(modelMap, date, 1, 10);
        return "list";
    }

    @GetMapping(value = "/{date}/{pageNum}")
    public String date(ModelMap modelMap, @PathVariable String date, @PathVariable Integer pageNum) {
        service.listDate(modelMap, date, pageNum, 10);
        return "list";
    }

    @GetMapping(value = "/{date}/{pageNum}/{pageSize}")
    public String date(ModelMap modelMap, @PathVariable String date, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        service.listDate(modelMap, date, pageNum, pageSize);
        return "list";
    }

}
