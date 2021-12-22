package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StDataDao;
import cn.sixlab.minesoft.singte.core.models.StData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/data")
public class DataController extends BaseController {

    @Autowired
    private StDataDao dataMapper;

    @GetMapping(value = "/page/{random}")
    public String page(ModelMap modelMap, @PathVariable Integer random) {
        return "data/page";
    }

    @ResponseBody
    @PostMapping(value = "/api/{group}")
    public ModelResp fetch(ModelMap modelMap, @PathVariable String group) {

        String key = String.valueOf(new Random().nextInt(90000) + 10000);
        StData data = dataMapper.selectByGroupKey(group, key);
        if (null == data) {
            data = new StData();
            data.setDataGroup(group);
            data.setDataKey(key);
            data.setCreateTime(new Date());
            dataMapper.save(data);
        } else if ((new Date().getTime() - data.getCreateTime().getTime()) <= StConst.SECONDS_MIN_10 * 1000) {
            return ModelResp.error(5000, "已被占用，请重试！");
        }

        return ModelResp.success(key);
    }

    @ResponseBody
    @PostMapping(value = "/v/{group}/{key}")
    public ModelResp val(ModelMap modelMap, @PathVariable String group, @PathVariable String key) {
        StData data = dataMapper.selectByGroupKey(group, key);

        if (null != data) {
            return ModelResp.success(data.getDataContent());
        } else {
            return ModelResp.error(5404, "数据不存在！");
        }
    }

}
