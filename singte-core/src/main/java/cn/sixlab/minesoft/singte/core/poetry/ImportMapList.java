package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("importmaplist")
public class ImportMapList extends PoetryImportApi {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        list.add(new PoetryModel("mengxue/zengguangxianwen.json", "经部", "蒙学类", "增广贤文", "佚名"));
        list.add(new PoetryModel("mengxue/wenzimengqiu.json", "经部", "蒙学类", "文字蒙求", "王筠"));

        return list;
    }

    /**
     * mengxue/zengguangxianwen.json
     * mengxue/wenzimengqiu.json
     *
     */
    @Override
    public void parseJson(String resp, SteAncientSection param) {
        SteAncientSection ancientSection = new SteAncientSection();
        BeanUtil.copyProperties(param, ancientSection);

        int sectionCount = 1;
        Map topMap = JsonUtils.toBean(resp, Map.class);
        List<Map> mapList = (List<Map>) topMap.get("content");

        param.setBookName(MapUtil.getStr(topMap, "title"));

        for (Map map : mapList) {

            ancientSection.setId(null);
            ancientSection.setWeight(sectionCount++);

            String title = MapUtil.getStr(map, "title");
            if (StrUtil.isEmpty(title)) {
                title = MapUtil.getStr(map, "chapter");
            }
            ancientSection.setSectionName(title);

            List<String> contentList = (List<String>) map.get("paragraphs");
            ancientSection.setContentHtml(StrUtil.join("<br />", contentList));
            ancientSection.setContentText(StrUtil.join("", contentList));

            ancientSectionDao.save(ancientSection);
        }
    }
}
