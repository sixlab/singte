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

@Component("importdizigui")
public class ImportDiZiGui extends PoetryImportApi {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list= new ArrayList<>();

        list.add(new PoetryModel("mengxue/dizigui.json", "经部", "蒙学类", "弟子规", "李毓秀"));

        return list;
    }

    /**
     * mengxue/dizigui.json
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
        param.setAuthor(MapUtil.getStr(topMap, "author"));

        for (Map map : mapList) {
            ancientSection.setId(null);
            ancientSection.setWeight(sectionCount++);

            String title = MapUtil.getStr(map, "title");
            if (StrUtil.isEmpty(title)) {
                title = MapUtil.getStr(map, "chapter");
            }
            ancientSection.setSectionName(title);

            List<String> contentList = (List<String>) map.get("paragraphs");
            String contentText = StrUtil.join("。", contentList);
            contentText = StrUtil.replace(contentText, " ", "，");

            ancientSection.setContentHtml(StrUtil.replace(contentText, "。", "。<br />"));
            ancientSection.setContentText(contentText);

            ancientSectionDao.save(ancientSection);
        }
    }
}
