package cn.sixlab.minesoft.singte.module.minesoft.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.mapper.SeoDataMapper;
import cn.sixlab.minesoft.singte.core.mapper.SeoItemMapper;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoData;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoItem;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/seo/data")
public class SeoDataController extends BaseController {

    @Autowired
    private SeoItemMapper itemMapper;

    @Autowired
    private SeoDataMapper dataMapper;

    @GetMapping(value = "/list")
    public String list(ModelMap modelMap) {

        return "seo/data/list";
    }

    @ResponseBody
    @PostMapping(value = "/lastData")
    public ModelResp lastData() {
        Date days = DateUtils.addDays(new Date(), -8);

        List<SeoData> dataList = dataMapper.selectBeforeDate(DateFormatUtils.format(days, "yyyyMMdd"));

        List<String> dateList = new ArrayList<>();
        Set<String> userList = new HashSet<>();
        Set<String> lineList = new HashSet<>();
        Map<String, ArrayList<Integer>> dataMap = new HashMap<>();

        for (SeoData seoData : dataList) {
//            日期
            if (!dateList.contains(seoData.getDate())) {
                dateList.add(seoData.getDate());
            }

//            用户
            userList.add(seoData.getUser());
            String userCategory = seoData.getUser() + "-" + seoData.getCategory();
            if (lineList.add(userCategory)) {
                dataMap.put(userCategory, new ArrayList<>());
            }

//            数据
            dataMap.get(userCategory).add(seoData.getIp());
        }

        ModelResp resp = ModelResp.success();
        resp.add("dateList", dateList);
        resp.add("lineList", lineList);
        resp.add("userList", userList);
        resp.add("dataMap", dataMap);
        return resp;
    }

    @GetMapping(value = "/category/{category}")
    public String category(ModelMap modelMap, @PathVariable String category) {

        modelMap.put("category", category);

        return "seo/data/category";
    }

    @ResponseBody
    @PostMapping(value = "/category/{category}")
    public ModelResp page(@PathVariable String category) {
        List<SeoItem> itemList = itemMapper.selectByCategory(category);

        Date days = DateUtils.addDays(new Date(), -1);
        String[] dateList = new String[7];
        int i = dateList.length;
        while (i-- > 0) {
            dateList[i] = DateFormatUtils.format(days, "yyyyMMdd");
            days = DateUtils.addDays(days, -1);
        }

        Map<String, List<SeoData>> dataMap = new HashMap<>();
        for (SeoItem seoItem : itemList) {
            List<SeoData> dataList = new ArrayList<>();

            for (String date : dateList) {
                SeoData data = dataMapper.selectUserDateCategory(seoItem.getUid(), date, category);
                if (null == data) {
                    data = new SeoData();
                    data.setIp(0);
                    data.setPv(0);
                }
                dataList.add(data);
            }

            dataMap.put(seoItem.getUid(), dataList);
        }

        ModelResp resp = ModelResp.success();
        resp.add("itemList", itemList);
        resp.add("dateList", dateList);
        resp.add("dataMap", dataMap);
        return resp;
    }

}
