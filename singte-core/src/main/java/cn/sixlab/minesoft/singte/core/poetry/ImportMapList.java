package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("importmaplist")
public class ImportMapList extends PoetryImportApi {

    /**
     * mengxue/zengguangxianwen.json
     * mengxue/wenzimengqiu.json
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
            param.setId(null);
            param.setWeight(sectionCount++);

            String title = MapUtil.getStr(map, "title");
            if (StrUtil.isEmpty(title)) {
                title = MapUtil.getStr(map, "chapter");
            }
            param.setSectionName(title);

            List<String> contentList = (List<String>) map.get("paragraphs");
            param.setContentHtml(StrUtil.join("<br />", contentList));
            param.setContentText(StrUtil.join("", contentList));

            ancientSectionDao.save(param);
        }
    }
}
