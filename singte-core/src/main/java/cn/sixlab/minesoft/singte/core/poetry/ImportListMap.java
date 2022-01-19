package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("importlistmap")
public class ImportListMap extends PoetryImportApi {

    /**
     * huajianji/huajianji-0-preface.json
     * huajianji/huajianji-1-juan.json
     * huajianji/huajianji-2-juan.json
     * huajianji/huajianji-3-juan.json
     * huajianji/huajianji-4-juan.json
     * huajianji/huajianji-5-juan.json
     * huajianji/huajianji-6-juan.json
     * huajianji/huajianji-7-juan.json
     * huajianji/huajianji-8-juan.json
     * huajianji/huajianji-9-juan.json
     * huajianji/huajianji-x-juan.json
     * yuanqu/yuanqu.json
     * nantang/poetrys.json
     * nantang/poetrys.json
     * sishuwujing/mengzi.json
     * shijing/shijing.json
     * quan_tang_shi/json/001.json
     * quan_tang_shi/json/900.json
     *
     */
    @Override
    public void parseAncient(String type, String resp, SteAncientSection param) {
        int sectionCount = 1;
        List<Map> mapList = JsonUtils.toBean(resp, List.class);

        for (Map map : mapList) {
            param.setId(null);
            param.setWeight(sectionCount++);

            String title = MapUtil.getStr(map, "title");
            String chapter = MapUtil.getStr(map, "chapter");
            String section = MapUtil.getStr(map, "section");
            String volume = MapUtil.getStr(map, "volume");
            if (StrUtil.isEmpty(title) && StrUtil.isNotEmpty(chapter)) {
                title = chapter;
            }else if(StrUtil.isAllNotEmpty(title, chapter, section)){
                title = chapter + "·" + section + "·" + title;
            }else if(StrUtil.isAllNotEmpty(title, volume)){
                title = volume + "·" + title;
            }
            param.setSectionName(title);

            String author = MapUtil.getStr(map, "author");
            if(StrUtil.isNotEmpty(author)){
                param.setAuthor(author);
            }

            List<String> contentList = (List<String>) map.get("paragraphs");
            param.setContentHtml(StrUtil.join("<br />", contentList));
            param.setContentText(StrUtil.join("", contentList));

            ancientSectionDao.save(param);
        }
    }
}
