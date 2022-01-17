package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.crypto.digest.BCrypt;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.models.StUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

    @Autowired
    private StUserDao userDao;

    @GetMapping(value = "/list")
    public String list() {
        return "admin/user/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StUser> pageResult = userDao.selectUsers(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/user/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submitUser")
    public ModelResp submitUser(StUser stUser) {
        stUser.setPassword(BCrypt.hashpw(stUser.getPassword()));
        stUser.setStatus(StConst.YES);
        stUser.setCreateTime(new Date());
        userDao.save(stUser);
        return ModelResp.success();
    }

}
