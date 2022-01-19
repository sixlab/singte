package cn.sixlab.minesoft.singte.core.poetry;

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
        int sectionCount = 1;
        Map topMap = JsonUtils.toBean(resp, Map.class);
        List<Map> mapList = (List<Map>) topMap.get("content");

        param.setBookName(MapUtil.getStr(topMap, "title"));
        param.setAuthor(MapUtil.getStr(topMap, "author"));

        for (Map map : mapList) {
            param.setId(null);
            param.setWeight(sectionCount++);

            String title = MapUtil.getStr(map, "title");
            if (StrUtil.isEmpty(title)) {
                title = MapUtil.getStr(map, "chapter");
            }
            param.setSectionName(title);

            List<String> contentList = (List<String>) map.get("paragraphs");
            String contentText = StrUtil.join("。", contentList);
            contentText = StrUtil.replace(contentText, " ", "，");

            param.setContentHtml(StrUtil.replace(contentText, "。", "。<br />"));
            param.setContentText(contentText);

            ancientSectionDao.save(param);
        }
    }
}
