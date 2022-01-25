package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import cn.sixlab.minesoft.singte.core.common.pager.PageResult;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.common.vo.StModelTable;
import cn.sixlab.minesoft.singte.core.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/admin/table")
public class AdminTableController extends BaseController {

    @Autowired
    private TableService tableService;

    @GetMapping(value = "/{tableName}/list")
    public String list(ModelMap modelMap, @PathVariable String tableName) {

        StModelTable tableInfo = tableService.getTableInfo(tableName);

        modelMap.put("tableInfo", tableInfo);
        modelMap.put("columns", tableService.getColumns(tableName, true));

        return "admin/table/list";
    }

    @PostMapping(value = "/{tableName}/listData")
    public String listData(ModelMap modelMap, @PathVariable String tableName,
                           String keyword, String status,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {
        BaseDao baseDao = tableService.getDao(tableName);

        PageResult<BaseModel> pageResult = baseDao.queryData(keyword, status, pageNum, pageSize);

        modelMap.put("result", pageResult);
        modelMap.put("columns", tableService.getColumns(tableName, false));

        return "admin/table/listData";
    }

    @ResponseBody
    @RequestMapping(value = "/{tableName}/submit")
    public ModelResp submit(@PathVariable String tableName, HttpServletRequest request) {
        BaseModel params = tableService.getParams(tableName, request);

        BaseDao baseDao = tableService.getDao(tableName);
        BaseModel nextInfo;

        BaseModel checkExist = baseDao.selectExist(params);

        if (StrUtil.isNotEmpty(params.getId())) {
            nextInfo = baseDao.selectById(params.getId());

            if (null == nextInfo) {
                return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
            }

            if (null != checkExist && !params.getId().equals(checkExist.getId())) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo.setUpdateTime(new Date());
        } else {
            if (null != checkExist) {
                return ModelResp.error(StErr.EXIST_SAME, "common.same.found");
            }

            nextInfo = tableService.newModel(tableName);
        }

        BeanUtil.copyProperties(params, nextInfo, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));

        baseDao.save(nextInfo);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/{tableName}/status")
    public ModelResp status(@PathVariable String tableName, String id, String status) {
        BaseDao baseDao = tableService.getDao(tableName);

        BaseModel record = baseDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        record.setStatus(status);
        baseDao.save(record);
        return ModelResp.success();
    }

    @ResponseBody
    @RequestMapping(value = "/{tableName}/get")
    public ModelResp get(@PathVariable String tableName, String id) {
        BaseDao baseDao = tableService.getDao(tableName);

        BaseModel record = baseDao.selectById(id);

        if (null == record) {
            return ModelResp.error(StErr.NOT_EXIST, "common.not.found");
        }

        return ModelResp.success(record);
    }

    @ResponseBody
    @RequestMapping(value = "/{tableName}/delete")
    public ModelResp delete(@PathVariable String tableName, String id) {
        BaseDao baseDao = tableService.getDao(tableName);

        baseDao.deleteById(id);

        return ModelResp.success();
    }

}
