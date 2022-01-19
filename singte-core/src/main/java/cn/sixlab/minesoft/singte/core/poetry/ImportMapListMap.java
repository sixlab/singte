package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("importmaplistmap")
public class ImportMapListMap extends PoetryImportApi {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        list.add(new PoetryModel("mengxue/youxueqionglin.json", "经部", "蒙学类", "幼学琼林", "程登吉"));
        list.add(new PoetryModel("mengxue/tangshisanbaishou.json", "经部", "蒙学类", "唐诗三百首", "王筠"));
        list.add(new PoetryModel("mengxue/shenglvqimeng.json", "经部", "蒙学类", "声律启蒙", "车万育"));
        list.add(new PoetryModel("mengxue/qianjiashi.json", "经部", "蒙学类", "千家诗", "南宋謝枋得/明代王相"));
        list.add(new PoetryModel("mengxue/guwenguanzhi.json", "经部", "蒙学类", "古文观止", "吳楚材、吳調侯"));

        return list;
    }

    /**
     * mengxue/youxueqionglin.json
     * mengxue/tangshisanbaishou.json
     * mengxue/shenglvqimeng.json
     * mengxue/qianjiashi.json
     * mengxue/guwenguanzhi.json
     */
    @Override
    public void parseJson(String resp, SteAncientSection param) {
        int sectionCount = 1;
        Map topMap = JsonUtils.toBean(resp, Map.class);
        List<Map> mapList = (List<Map>) topMap.get("content");

        param.setBookName(MapUtil.getStr(topMap, "title"));

        if (topMap.containsKey("author")) {
            param.setAuthor(MapUtil.getStr(topMap, "author"));
        }

        for (Map middleMap : mapList) {
            String parentTitle = MapUtil.getStr(middleMap, "title");
            List<Map> finalContent = (List<Map>) middleMap.get("content");

            for (Map finalMap : finalContent) {
                param.setId(null);
                param.setWeight(sectionCount++);

                String finalTitle = MapUtil.getStr(finalMap, "chapter");
                if(StrUtil.isNotEmpty(parentTitle)){
                    finalTitle = parentTitle + "·" + finalTitle;
                }
                param.setSectionName(finalTitle);

                if (finalMap.containsKey("author")) {
                    String author = MapUtil.getStr(finalMap, "author");
                    if (finalMap.containsKey("source")) {
                        author = author + " - " + MapUtil.getStr(finalMap, "source");
                    }
                    param.setAuthor(author);
                }

                List<String> contentList = (List<String>) finalMap.get("paragraphs");
                param.setContentHtml(StrUtil.join("<br />", contentList));
                param.setContentText(StrUtil.join("", contentList));

                ancientSectionDao.save(param);
            }
        }
    }
}
