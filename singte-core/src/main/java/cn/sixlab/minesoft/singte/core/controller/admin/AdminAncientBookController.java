package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
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
                           @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<SteAncientBook> pageResult = ancientBookDao.selectBooks(null, keyword, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/ancient/bookListData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitBook")
    public ModelResp submitBook(SteAncientBook steAncientBook) {
        steAncientBook.setCreateTime(new Date());
        ancientBookDao.save(steAncientBook);
        return ModelResp.success();
    }

}
