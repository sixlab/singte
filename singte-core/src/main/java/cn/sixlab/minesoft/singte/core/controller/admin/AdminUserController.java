package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
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

    @GetMapping(value = "/info")
    public String info(ModelMap modelMap) {

        modelMap.put("token", WebUtils.getToken());

        return "admin/user/info";
    }

    @GetMapping(value = "/list")
    public String list() {
        return "admin/user/list";
    }

    @PostMapping(value = "/listData")
    public String listData(ModelMap modelMap, String keyword, String status,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {

        PageResult<StUser> pageResult = userDao.queryData(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);

        return "admin/user/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/submit")
    public ModelResp submit(StUser params) {
        StUser nextInfo;
        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = userDao.selectById(params.getId());

            if (null == nextInfo) {
                return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
            }

            nextInfo.setUpdateTime(new Date());
        } else {
            StUser checkExist = userDao.selectExist(params);
            if (null != checkExist) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo = new StUser();
            nextInfo.setUsername(params.getUsername());
            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());
        }

        nextInfo.setShowName(params.getShowName());
        nextInfo.setMobile(params.getMobile());
        nextInfo.setEmail(params.getEmail());
        nextInfo.setRole(params.getRole());

        String password = params.getPassword();
        if (StrUtil.isNotEmpty(password)) {
            nextInfo.setPassword(BCrypt.hashpw(password));
        }

        userDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public ModelResp status(String id, String status) {
        StUser record = userDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        userDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ModelResp get(String id) {
        StUser record = userDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public ModelResp delete(String id) {
        userDao.deleteById(id);

        return ModelResp.success();
    }

}
