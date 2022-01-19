package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("importmaplist")
public class ImportMapListMap extends PoetryImportApi {

    /**
     * mengxue/youxueqionglin.json
     *
     */
    @Override
    public void parseAncient(String type, String resp, SteAncientSection param) {
        int sectionCount = 1;
        Map diziguiMap = JsonUtils.toBean(resp, Map.class);
        List<Map> diziguiContent = (List<Map>) diziguiMap.get("content");

        param.setBookName(MapUtil.getStr(diziguiMap, "title"));
        param.setAuthor(MapUtil.getStr(diziguiMap, "author"));

        for (Map map : diziguiContent) {
            String dzgTitle = MapUtil.getStr(map, "title");
            List<Map> finalContent = (List<Map>) map.get("content");

            for (Map finalMap : finalContent) {
                param.setId(null);
                param.setWeight(sectionCount++);

                String finalTitle = dzgTitle + "·" + MapUtil.getStr(finalMap, "chapter");

                param.setSectionName(finalTitle);

                List<String> contentList = (List<String>) finalMap.get("paragraphs");
                param.setContentHtml(StrUtil.join("<br />", contentList));
                param.setContentText(StrUtil.join("", contentList));

                ancientSectionDao.save(param);
            }
        }
    }
}
