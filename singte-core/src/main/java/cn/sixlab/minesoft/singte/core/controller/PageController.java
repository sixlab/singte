
package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.dao.StPageDao;
import cn.sixlab.minesoft.singte.core.models.StPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController extends BaseController {

    @Autowired
    private StPageDao pageDao;

    @GetMapping(value = "/page/{pageId}")
    public String page(ModelMap modelMap, @PathVariable String pageId) {
        StPage page = pageDao.selectByAlias(pageId);
        if (page == null) {
            page = pageDao.selectById(pageId);
        }

        if (null == page) {
            return "redirect:/404";
        } else {
            pageDao.addView(page.getId());

            modelMap.put("page", page);
            return "page";
        }
    }

}
